package com.example.project9

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.project9.databinding.FragmentSelfieBinding

class SelfieFragment: Fragment() {

    val TAG = "PostsFragment"
    private var _binding: FragmentSelfieBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelfieBinding.inflate(inflater, container, false)
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val view = binding.root
        val viewModelAccel: SensorsViewModel by activityViewModels()
        binding.viewModelAccel = viewModelAccel
        binding.lifecycleOwner = viewLifecycleOwner
        val url = SelfieFragmentArgs.fromBundle(requireArguments())
        Glide.with(requireContext()).load(url.url).into(binding.ivSelfie)

        viewModelAccel.initializeAccel(Accelerometer(this.requireContext()))
        viewModelAccel.shakeEvent.observe(viewLifecycleOwner) { isShaken ->
            if (isShaken) {
                viewModelAccel.shakeEvent.value = false
                val navController = view.findNavController()
                if (navController.currentDestination?.id != R.id.takePicture) {
                    navController.navigate(R.id.action_selfie_to_picture)
                }
            }
        }
        return view
    }
}