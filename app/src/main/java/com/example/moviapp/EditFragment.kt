package com.example.moviapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moviapp.databinding.FragmentEditBinding
import com.example.moviapp.db.MyDbHelper
import com.example.moviapp.models.Movie

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding

    lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(requireContext())

        var movie: Movie? = null

        try {
            movie = arguments?.getSerializable("keyMovie") as Movie

        } catch (e: Exception) {
        }
        binding.apply {
            if (movie != null) {
                nameEditText.setText(movie.name)
                authorsEditText.setText(movie.authors)
                infoEditText.setText(movie.info)
                dateEditText.setText(movie.date)

                editButton.setOnClickListener {
                    movie.name = nameEditText.text.toString()
                    movie.authors = authorsEditText.text.toString()
                    movie.info = infoEditText.text.toString()
                    movie.date = dateEditText.text.toString()

                    myDbHelper.edit(movie)

                    Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }
        return binding.root
    }
}