package com.example.project9.model

import android.net.Uri
import com.google.firebase.firestore.PropertyName

data class Selfie(
    @get:PropertyName("image_url") @set:PropertyName("image_url")
    var imageUrl: String = "",
    @get:PropertyName("user") @set:PropertyName("user")
    var user: User? = null
)