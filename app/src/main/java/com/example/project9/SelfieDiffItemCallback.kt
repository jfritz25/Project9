package com.example.project9

import androidx.recyclerview.widget.DiffUtil
import com.example.project9.model.Selfie

class SelfieDiffItemCallback : DiffUtil.ItemCallback<Selfie>() {
    override fun areItemsTheSame(oldItem: Selfie, newItem: Selfie)
            = (oldItem.imageUrl == newItem.imageUrl)
    override fun areContentsTheSame(oldItem: Selfie, newItem: Selfie) = (oldItem == newItem)
}