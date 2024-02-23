package com.example.moviapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moviapp.databinding.FragmentInfoBinding
import com.example.moviapp.db.MyDbHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFagment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFagment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)

        val receiveId = arguments?.getInt("ID", 0);

        myDbHelper = MyDbHelper(requireContext())

        val array = myDbHelper.getAllMovie()

        binding.nameText.text = array[receiveId!!].name
        binding.authorsText.text = array[receiveId!!].authors
        binding.infoText.text = array[receiveId!!].info
        binding.dateText.text = array[receiveId!!].date

        binding.closeButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        return binding.root
    }

}