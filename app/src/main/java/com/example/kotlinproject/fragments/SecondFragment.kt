/*
Lauri Riikonen
1909911
 */
package com.example.kotlinproject.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.R
import com.example.kotlinproject.Word
import com.example.kotlinproject.badlearnedwords.BadLearnedWord
import com.example.kotlinproject.badlearnedwords.BadLearnedWordDao
import com.example.kotlinproject.badlearnedwords.BadLearnedWordDatabase
import com.example.kotlinproject.badlearnedwords.BadLearnedWordRepository
import com.example.kotlinproject.data.FakeDatabase
import com.example.kotlinproject.data.FakeWordDao
import com.example.kotlinproject.data.WordRepository
import com.example.kotlinproject.databinding.FragmentSecondBinding
import com.example.kotlinproject.factories.SecondViewModelFactory
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

        initializeUI()

        viewModel.buttonPressed.observe(viewLifecycleOwner, Observer<Boolean> { wasPressed ->
          if (wasPressed) checkResult() //--> If buttonPressed = true, (Happens on click)
        })

        return binding.root
    }


    private fun checkResult() {

        //Randomed word in the viewmodel
        val randomedWord = viewModel.word.value

        //Language to translate into
        val translationLanguage = "English"

        //Edit text field
        val userInput = binding.userInput.text.toString().toLowerCase().trim()

        //If user input is correct, increment score
        if (randomedWord?.isTranslation(Word(translationLanguage, userInput)) == true) {
            viewModel.incrementScore()
            viewModel.selectWord()
            binding.userInput.setText("")
        // Else decrement the score
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


    //Choices in menu, possibility for more buttons
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.info -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Ok") { _, _ -> }
                builder.setTitle("Info")
                builder.setMessage("Type in the matching English word")
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeUI() {

        //Change action bar title
        (activity as AppCompatActivity).supportActionBar?.title = "Game 1"

        //Parameters for SecondViewModelFactory
        val application = requireNotNull(this.activity).application

        val userDao = BadLearnedWordDatabase.getInstance(application).badLearnedWordDao

        //No actual usage
        val datasource2 = BadLearnedWordRepository(userDao)

        //Stored words are here
        val dataSource1 = WordRepository.getInstance(FakeWordDao())

        val factory = SecondViewModelFactory(dataSource1, datasource2, application)


        //Create / get already created SecondViewModel
        //for this view (activity)
        viewModel = ViewModelProvider(this, factory).get(SecondViewModel::class.java)


        /**
         * Specify the fragment view as the lifecycle owner of the binding
         * This is used so that the binding can observe LiveData updates
         */
        binding.secondViewModel = viewModel

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        //Observer for button
        viewModel.buttonPressed.observe(viewLifecycleOwner, Observer<Boolean> { wasPressed ->
            if (wasPressed) {       //--> If buttonPressed = true, (Happens on click)
                checkResult()
            }
        })

    }

}