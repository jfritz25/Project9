package com.example.project9

import android.os.Bundle
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelfiesBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel : SelfieViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = PostsAdapter(this.requireContext())
        binding.rvSelfies.adapter = adapter
        viewModel.selfies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
//                adapter.notifyDataSetChanged()
            }
        })

        binding.logout.setOnClickListener {
            viewModel.signOut()
            view.findNavController().navigate(R.id.action_selfies_to_login)
        }
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.toolbar.inflateMenu(R.menu.menu_posts)
//        binding.toolbar.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.menu_profile -> {
//                    // Navigate to profile screen.
//                    view.findNavController().navigate(R.id.action_postsFragment_to_profileFragment)
//                    true
//                }
//                else -> false
//            }
//        }
//    }






}