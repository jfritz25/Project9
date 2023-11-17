package com.example.project9

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.project9.databinding.FragmentSelfiesBinding

class SelfiesFragment : Fragment() {
    /**
     * Fragment responsible for displaying a list of selfies and handling accelerometer shake events.
     */

    val TAG = "PostsFragment"
    private var _binding: FragmentSelfiesBinding? = null
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
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        _binding = FragmentSelfiesBinding.inflate(inflater, container, false)
        val view = binding.root

        // Obtain the shared ViewModel for selfie data and accelerometer data
        val viewModel : SelfieViewModel by activityViewModels()
        val viewModelAccel: SensorsViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Set up RecyclerView adapter for selfies
        val adapter = SelfieAdapter(this.requireContext())
        binding.rvSelfies.adapter = adapter

        // Initialize accelerometer sensor and observe shake events
        viewModelAccel.initializeAccel(Accelerometer(this.requireContext()))
        viewModel.selfies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Observe shake events to navigate to "takePicture" destination
        viewModelAccel.shakeEvent.observe(viewLifecycleOwner) { isShaken ->
            if (isShaken) {
                viewModelAccel.shakeEvent.value = false
                view.findNavController().navigate(R.id.action_selfies_to_picture)
            }
        }

        // Handle logout button click to sign out and navigate to the login screen
        binding.logout.setOnClickListener {
            viewModel.signOut()
            view.findNavController().navigate(R.id.action_selfies_to_login)
        }
        return view
    }







}
