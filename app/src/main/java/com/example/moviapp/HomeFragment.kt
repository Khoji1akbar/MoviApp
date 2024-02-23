package com.example.moviapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moviapp.adapter.MovieAdapter
import com.example.moviapp.databinding.FragmentHomeBinding
import com.example.moviapp.db.MyDbHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var myDbHelper: MyDbHelper

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(requireContext())

        movieAdapter =
            MovieAdapter(requireContext(), myDbHelper.getAllMovie(), object : MovieAdapter.RvClick {
                override fun onClick(position: Int) {
                    val bundle = Bundle()
                    bundle.putInt("ID", position)
                    findNavController().navigate(R.id.infoFragment, bundle)
                }
            })

        binding.rv.adapter = movieAdapter

        binding.apply {
            addButton.setOnClickListener {
                findNavController().navigate(R.id.addFragment)
            }
        }

        return binding.root
    }
}