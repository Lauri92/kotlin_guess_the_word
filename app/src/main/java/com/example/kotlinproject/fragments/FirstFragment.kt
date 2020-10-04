package com.example.kotlinproject.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentFirstBinding
import com.example.kotlinproject.utilities.InjectorUtils
import com.example.kotlinproject.viewmodels.FirstViewModel


class FirstFragment : Fragment() {


    private lateinit var viewModel: FirstViewModel

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_first, container, false)


        val factory = InjectorUtils.provideFirstViewModel()

        viewModel = ViewModelProvider(this, factory).get(FirstViewModel::class.java)

        binding.firstViewModel = viewModel

        binding.lifecycleOwner = this

        binding.playGameButton.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment())
        }

        binding.playGameButton2.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToThirdFragment())
        }

        binding.wordlistButton.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToWordlistFragment())
        }

        setHasOptionsMenu(true)

        return binding.root

    }

    //Inflate the options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.info_menu, menu)
    }


    //When statement for winner menu choices
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.info -> {          //Toast.makeText(context, "Hello testing", Toast.LENGTH_SHORT).show()
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Okay") { _, _ -> }
                builder.setTitle("Info")
                builder.setMessage("Choose a game to play")
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}