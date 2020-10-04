package com.example.kotlinproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.R
import com.example.kotlinproject.Word
import com.example.kotlinproject.databinding.FragmentThirdBinding
import com.example.kotlinproject.utilities.InjectorUtils
import com.example.kotlinproject.viewmodels.ThirdViewModel
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : Fragment() {

    private lateinit var viewModel: ThirdViewModel

    private lateinit var binding: FragmentThirdBinding

    private lateinit var answers: MutableList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_third, container, false
        )

        val factory = InjectorUtils.provideThirdViewModel()

        viewModel = ViewModelProvider(this, factory).get(ThirdViewModel::class.java)

        binding.thirdViewModel = viewModel

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        setQuestion()

        binding.button3.setOnClickListener { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            if (-1 != checkedId) {
                var answerIndex = 0

                val oikea = checkCorrectRadioButton()

                when (checkedId) {
                    R.id.firstAnswerRadioButton -> answerIndex = 0
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                Log.i("ThirdFragment", "answerIndex = $answerIndex")
                Log.i("ThirdFragment", "${viewModel.currentQuiz.value}")


                if (answerIndex == oikea) {
                    Log.i("ThirdFragment", "Correct answer")
                    viewModel.createNewQuiz()
                } else {
                    Log.i("ThirdFragment", "Wrong answer, try again")
                }
            }

        }

        return binding.root
    }

    private fun setQuestion() {
        // randomize the answers into a copy of the array
        answers = viewModel.currentQuiz.value!!.allAnswers.toMutableList()
        // and shuffle them
        answers.shuffle()
    }

    fun checkCorrectRadioButton(): Int {
        val oikea = viewModel.currentQuiz.value?.correctAnswer

        val firstButtonText: String = binding.firstAnswerRadioButton.text.toString()
        val secondButtonText: String = binding.secondAnswerRadioButton.text.toString()
        val thirdButtonText: String = binding.thirdAnswerRadioButton.text.toString()
        val fourthButtonText: String = binding.fourthAnswerRadioButton.text.toString()

        Log.i(
            "ThirdFragment",
            "1${firstButtonText} 2${secondButtonText} 3${thirdButtonText} 4${fourthButtonText}"
        )

        return if (oikea!!.isTranslation(Word("English", firstButtonText))) {
            0
        } else if (oikea.isTranslation(Word("English", secondButtonText))) {
            1
        } else if (oikea.isTranslation(Word("English", thirdButtonText))) {
            2
        } else {
            3
        }
    }


}