package com.example.moviapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moviapp.databinding.FragmentAddBinding
import com.example.moviapp.db.MyDbHelper
import com.example.moviapp.models.Movie

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding

    private lateinit var myDbHelper: MyDbHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(requireContext())


        binding.apply {
            saveButton.setOnClickListener {
                if (nameEditText.text.isNullOrBlank() || authorsEditText.text.isNullOrBlank() || infoEditText.text.isNullOrBlank() || dateEditText.text.isNullOrBlank()) {
                    Toast.makeText(context, "Toldiring!!!", Toast.LENGTH_SHORT).show()
                } else {
                    val movie = Movie(
                        nameEditText.text.toString(),
                        authorsEditText.text.toString(),
                        dateEditText.text.toString(),
                        infoEditText.text.toString()
                    )

                    myDbHelper.add(movie)
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }



        return binding.root
    }
}