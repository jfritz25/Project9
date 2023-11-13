package com.example.project9
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

    class ImageViewModel : ViewModel() {
        private var auth: FirebaseAuth

        var user: User = User()
        var verifyPassword = ""
        var noteId: String = ""
        var note = MutableLiveData<Image>()
        private val _images: MutableLiveData<MutableList<Image>> = MutableLiveData()
        val image: LiveData<List<Image>>
            get() = _images as LiveData<List<Image>>
        //Below are variables we will attach observers
        private val _navigateToImage = MutableLiveData<String?>()
        val navigateToImage: LiveData<String?>
            get() = _navigateToImage

        private val _errorHappened = MutableLiveData<String?>()
        val errorHappened: LiveData<String?>
            get() = _errorHappened

        private val _navigateToList = MutableLiveData<Boolean>(false)
        val navigateToList: LiveData<Boolean>
            get() = _navigateToList

        private val _navigateToSignUp = MutableLiveData<Boolean>(false)
        val navigateToSignUp: LiveData<Boolean>
            get() = _navigateToSignUp

        private val _navigateToSignIn = MutableLiveData<Boolean>(false)
        val navigateToSignIn: LiveData<Boolean>
            get() = _navigateToSignIn

        private lateinit var notesCollection: DatabaseReference

        //Adds initial list of notes to db
        init {
            auth = Firebase.auth
            if (noteId.trim() == "") {
                note.value = Image()
            }
            _images.value = mutableListOf<Image>()
        }

        fun initializeTheDatabaseReference() {
            val database = Firebase.database
            notesCollection = database
                .getReference("notes")
                .child(auth.currentUser!!.uid)

            notesCollection.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var imageList: ArrayList<Image> = ArrayList()
                    for (imgSnapShot in dataSnapshot.children) {
                        var image = imgSnapShot.getValue<Image>()
//                        image?.hashCode(image) = noteSnapshot.key!!
                        imageList.add(image!!)
                    }
                    _images.value = imageList
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("Database issue", "Something horrible has happened :(")
                }
            })

        }
        //Adds/updates notes
        fun updateNote() {
            if (noteId.trim() == "") {
                notesCollection.push().setValue(note.value)
            } else {
                notesCollection.child(noteId).setValue(note.value)
            }
            _navigateToList.value = true
        }

        fun deleteNote(noteId: String) {
            notesCollection.child(noteId).removeValue()
        }

        fun onImageCLicked(selectedImg: Image) {

        }

        fun onNewNoteClicked() {
            _navigateToNote.value = ""
            noteId = ""
            note.value = Note()
        }
        //Navigation components
        fun onNoteNavigated() {
            _navigateToImage.value = null
        }

        fun onNavigatedToList() {
            _navigateToList.value = false
        }

        fun navigateToSignUp() {
            _navigateToSignUp.value = true
        }

        fun onNavigatedToSignUp() {
            _navigateToSignUp.value = false
        }

        fun navigateToSignIn() {
            _navigateToSignIn.value = true
        }

        fun onNavigatedToSignIn() {
            _navigateToSignIn.value = false
        }

        fun navigateToProfile()
        {
            navigateToSignIn()
        }

        //Signs user in with given name and password
        fun signIn() {
            if (user.email.isEmpty() || user.password.isEmpty()) {
                _errorHappened.value = "Email and password cannot be empty."
                return
            }
            auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
                if (it.isSuccessful) {
                    initializeTheDatabaseReference()
                    _navigateToList.value = true
                } else {
                    _errorHappened.value = it.exception?.message
                }
            }
        }
        //Signs user up with given name and password
        fun signUp() {
            if (user.email.isEmpty() || user.password.isEmpty()) {
                _errorHappened.value = "Email and password cannot be empty."
                return
            }
            if (user.password != verifyPassword) {
                _errorHappened.value = "Password and verify do not match."
                return
            }
            auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
                if (it.isSuccessful) {
                    _navigateToSignIn.value = true
                } else {
                    _errorHappened.value = it.exception?.message
                }
            }
        }

        fun signOut() {
            auth.signOut()
            user = User()
            _navigateToSignIn.value = true
        }


        fun getCurrentUser(): FirebaseUser? {
            return auth.currentUser
        }
    }
}