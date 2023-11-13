package com.example.project9
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
//import com.example.project9.databinding.FragmentImageBinding

    class ImageFragment : Fragment()   {
        /**
         * A simple [Fragment] subclass.
         * Use the [NotesFragment.newInstance] factory method to
         * create an instance of this fragment.
         */
        val TAG = "ImageFragment"
        private var _binding: FragmentImageBinding? = null
        private val binding get() = _binding!!
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {

        }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }


    }
}