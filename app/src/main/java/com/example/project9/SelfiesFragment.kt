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

    val TAG = "PostsFragment"
    private var _binding: FragmentSelfiesBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        _binding = FragmentSelfiesBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel : SelfieViewModel by activityViewModels()
        val viewModelAccel: SensorsViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = SelfieAdapter(this.requireContext())
        binding.rvSelfies.adapter = adapter
        viewModelAccel.initializeAccel(Accelerometer(this.requireContext()))
        viewModel.selfies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModelAccel.shakeEvent.observe(viewLifecycleOwner) { isShaken ->
            if (isShaken) {
                viewModelAccel.shakeEvent.value = false
                view.findNavController().navigate(R.id.action_selfies_to_picture)
            }
        }
        binding.logout.setOnClickListener {
            viewModel.signOut()
            view.findNavController().navigate(R.id.action_selfies_to_login)
        }



        return view
    }







}