package com.example.kotlinproject.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.R
import com.example.kotlinproject.Word
import com.example.kotlinproject.databinding.FragmentSecondBinding
import com.example.kotlinproject.utilities.InjectorUtils
import com.example.kotlinproject.viewmodels.SecondViewModel
import java.util.*


class SecondFragment : Fragment() {

    private lateinit var viewModel: SecondViewModel

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_second, container, false
        )

        //viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        initializeUI()
        //binding.secondViewModel = viewModel

        /**
         * Specify the fragment view as the lifecycle owner of the binding
         * This is used so that the binding can observe LiveData updates
         */
        //binding.lifecycleOwner = viewLifecycleOwner
        //binding.lifecycleOwner = this

        //setHasOptionsMenu(true)

        //viewModel.buttonPressed.observe(viewLifecycleOwner, Observer<Boolean> { wasPressed ->
        //  if (wasPressed) checkResult() //--> If buttonPressed = true, (Happens on click)
        //})

        return binding.root
    }


    private fun checkResult() {

        val randomedWord = viewModel.word.value

        val translationLanguage = "English"

        val userInput = binding.userInput.text.toString().toLowerCase().trim()

        if (randomedWord?.isTranslation(Word(translationLanguage, userInput)) == true) {
            viewModel.incrementScore()
            viewModel.selectWord()
            binding.userInput.setText("")
        } else {
            viewModel.decrementScore()
        }

        viewModel.viewModelButtonpressedCheckDone()
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
                builder.setMessage("PH")
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeUI() {
        val factory = InjectorUtils.provideSecondViewModel()
        //Use ViewModelProvider class to create / get already created QuotesViewModel
        //for this view (activity)
        viewModel = ViewModelProvider(this, factory).get(SecondViewModel::class.java)

        binding.secondViewModel = viewModel

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        viewModel.buttonPressed.observe(viewLifecycleOwner, Observer<Boolean> { wasPressed ->
            if (wasPressed) {       //--> If buttonPressed = true, (Happens on click)
                checkResult()
            }
        })

    }

}