package com.example.project9

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project9.model.Selfie
import com.example.project9.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObjects


class SelfieViewModel : ViewModel() {


    val TAG = "SelfieViewModel"
    var signedInUser: User? = null
    private val _selfies: MutableLiveData<MutableList<Selfie>> = MutableLiveData()
    val selfies: LiveData<List<Selfie>>
        get() = _selfies as LiveData<List<Selfie>>

    init {

        val  firestoreDB = FirebaseFirestore.getInstance()
//        firestoreDB.collection("users")
//            .document(FirebaseAuth.getInstance().currentUser?.uid as String)
//            .get()
//            .addOnSuccessListener { userSnapshot ->
//                signedInUser = userSnapshot.toObject<User>()
//                Log.i(TAG, "signed in user: $signedInUser")
//            }
//            .addOnFailureListener { exception ->
//                Log.i(TAG, "Failure fetching signed in user", exception)
//            }
        var selfiesReference = firestoreDB
            .collection("selfies")
            .limit(30)
            .orderBy("creation_time_ms", Query.Direction.DESCENDING)

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