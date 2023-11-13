package com.example.project9.model

import android.net.Uri
import com.google.firebase.firestore.PropertyName

data class Selfie(
    @get:PropertyName("image_url") @set:PropertyName("image_url")
    var imageUrl: String = "",
    @get:PropertyName("creation_time_ms") @set:PropertyName("creation_time_ms")
    var user: User? = null
)