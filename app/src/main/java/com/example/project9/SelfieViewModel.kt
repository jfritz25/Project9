package com.example.project9

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project9.model.Selfie
import com.example.project9.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database

class SelfieViewModel : ViewModel() {
    /**
     * ViewModel class for managing the data related to selfies in the application.
     */


    val TAG = "SelfieViewModel"
    var signedInUser: User? = null
    var selfie = MutableLiveData<Selfie>()

    /** MutableLiveData holding a list of selfies as LiveData. */
    private val _selfies: MutableLiveData<MutableList<Selfie>> = MutableLiveData()
    val selfies: LiveData<MutableList<Selfie>>
        get() = _selfies

    init {
        /**
         * Initialization block for setting up the ViewModel.
         */
        val firestoreDB = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        signedInUser = User(auth.currentUser?.email.toString())
        var selfiesReference = firestoreDB
            .collection("selfies")
            .limit(30)

        selfiesReference.addSnapshotListener { snapshot, exception ->
            if (exception != null || snapshot == null) {
                Log.e(TAG, "Exception when querying selfies", exception)
                return@addSnapshotListener
            }
            val selfieList = snapshot.toObjects<Selfie>()
            _selfies.value = selfieList as MutableList<Selfie>
            for (selfie in selfieList) {
                Log.i(TAG, "selfie ${selfie}")
            }
        }
    }
    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        signedInUser = null
    }
}
