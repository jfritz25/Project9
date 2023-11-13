package com.example.project9

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project9.databinding.LayoutSelfieBinding
import com.example.project9.model.Selfie
import java.math.BigInteger
import java.security.MessageDigest

class SelfieAdapter(val context: Context)
    : ListAdapter<Selfie, SelfieAdapter.PostItemViewHolder>(SelfieDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PostItemViewHolder = PostItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, context)
    }

    class PostItemViewHolder(val binding: LayoutSelfieBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): PostItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutSelfieBinding.inflate(layoutInflater, parent, false)
                return PostItemViewHolder(binding)
            }
        }

        fun bind(selfie: Selfie, context: Context) {
            Glide.with(context).load(selfie.imageUrl).into(binding.ivSelfie)
            binding.ivSelfie.setOnClickListener {
                val action = SelfiesFragmentDirections.actionSelfiesToSelfie(selfie.imageUrl)
                binding.root.findNavController().navigate(action)
            }
        }
    }
}