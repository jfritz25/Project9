package com.example.project9

import androidx.recyclerview.widget.DiffUtil
import com.example.project9.model.Selfie

class PostDiffItemCallback : DiffUtil.ItemCallback<Selfie>() {
    override fun areItemsTheSame(oldItem: Selfie, newItem: Selfie)
            = (oldItem.description == newItem.description)
    override fun areContentsTheSame(oldItem: Selfie, newItem: Selfie) = (oldItem == newItem)
}