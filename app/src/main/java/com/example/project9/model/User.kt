package com.example.project9.model

import com.google.firebase.firestore.PropertyName

data class User(
    @get:PropertyName("email") @set:PropertyName("email")
    var email: String = "")