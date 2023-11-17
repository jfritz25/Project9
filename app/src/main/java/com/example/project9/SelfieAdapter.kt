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
    : ListAdapter<Selfie, SelfieAdapter.SelfieItemViewHolder>(SelfieDiffItemCallback()) {
    /**
     * RecyclerView adapter for displaying a list of selfies.
     *
     * @param context The context associated with the adapter.
     */


    /**
     * Called when RecyclerView needs a new [SelfieItemViewHolder] of the given type to represent an item.
     *
     * @param parent The parent view group that the [SelfieItemViewHolder] will be attached to.
     * @param viewType The view type of the new [SelfieItemViewHolder].
     * @return A new [SelfieItemViewHolder] that holds a view of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : SelfieItemViewHolder = SelfieItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder: SelfieItemViewHolder, position: Int) {
        /**
         * Called by RecyclerView to display the data at the specified position.
         *
         * @param holder The [SelfieItemViewHolder] that holds the view for each item.
         * @param position The position of the item within the adapter's data set.
         */
        val item = getItem(position)
        holder.bind(item, context)
    }

    class SelfieItemViewHolder(val binding: LayoutSelfieBinding)
        : RecyclerView.ViewHolder(binding.root) {
        /**
         * ViewHolder class for representing a single selfie item.
         *
         * @param binding The binding associated with the layout of the selfie item.
         */

        companion object {
            /**
             * Inflates a [SelfieItemViewHolder] from the specified parent view group.
             *
             * @param parent The parent view group to inflate the view from.
             * @return A new [SelfieItemViewHolder] instance.
             */
            fun inflateFrom(parent: ViewGroup): SelfieItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutSelfieBinding.inflate(layoutInflater, parent, false)
                return SelfieItemViewHolder(binding)
            }
        }

        fun bind(selfie: Selfie, context: Context) {
            /**
             * Binds the selfie data to the view.
             *
             * @param selfie The selfie object containing data to be displayed.
             * @param context The context associated with the adapter.
             */
            Glide.with(context).load(selfie.imageUrl).into(binding.ivSelfie)
            binding.ivSelfie.setOnClickListener {
                val action = SelfiesFragmentDirections.actionSelfiesToSelfie(selfie.imageUrl)
                binding.root.findNavController().navigate(action)
            }
        }
    }
}
