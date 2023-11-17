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
    /**
     * Fragment responsible for displaying a single selfie along with accelerometer shake detection.
     */

    val TAG = "PostsFragment"
    private var _binding: FragmentSelfieBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Called to have the fragment instantiate its user interface view.
         *
         * @param inflater The LayoutInflater object that can be used to inflate views.
         * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
         * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
         * @return The created view or null.
         */
        // Inflate the layout for this fragment
        _binding = FragmentSelfieBinding.inflate(inflater, container, false)
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val view = binding.root

        // Obtain the shared ViewModel for accelerometer data
        val viewModelAccel: SensorsViewModel by activityViewModels()
        binding.viewModelAccel = viewModelAccel
        binding.lifecycleOwner = viewLifecycleOwner

        // Load the selfie image from the arguments using Glide
        val url = SelfieFragmentArgs.fromBundle(requireArguments())
        Glide.with(requireContext()).load(url.url).into(binding.ivSelfie)

        // Initialize accelerometer sensor and observe shake events
        viewModelAccel.initializeAccel(Accelerometer(this.requireContext()))
        viewModelAccel.shakeEvent.observe(viewLifecycleOwner) { isShaken ->
            if (isShaken) {
                viewModelAccel.shakeEvent.value = false

                // Navigate to the "takePicture" destination on a shake event
                val navController = view.findNavController()
                if (navController.currentDestination?.id != R.id.takePicture) {
                    navController.navigate(R.id.action_selfie_to_picture)
                }
            }
        }
        return view
    }
}
