package com.example.project9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.project9.databinding.FragmentSelfieBinding

class SelfieFragment: Fragment() {

    val TAG = "PostsFragment"
    private var _binding: FragmentSelfieBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelfieBinding.inflate(inflater, container, false)
        val viewModelAccel = binding.viewModelAccel
        val view = binding.root
        val url = SelfieFragmentArgs.fromBundle(requireArguments())
        Glide.with(requireContext()).load(url.url).into(binding.ivSelfie)

        viewModelAccel.shakeEvent.observe(viewLifecycleOwner, Observer { isShaken ->
            if (isShaken) {
                viewModelAccel.shakeEvent.value = false
                view.findNavController().navigate(R.id.action_selfies_to_picture)
            }
        })
        return view
    }
}