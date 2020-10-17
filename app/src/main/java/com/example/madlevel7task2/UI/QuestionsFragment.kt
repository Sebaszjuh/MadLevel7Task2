package com.example.madlevel7task2.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel7task2.Model.Quiz
import com.example.madlevel7task2.R
import com.example.madlevel7task2.ViewModel.QuizViewModel
import kotlinx.android.synthetic.main.fragment_questions.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class QuestionsFragment : Fragment() {

    private val viewModel: QuizViewModel by activityViewModels()
    private var currentQuestion: Int = 0
    private var correctAnswer: String? = ""
    private var questions: ArrayList<Quiz> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeQuestions()

        btnConfirm.setOnClickListener {
            when (rgChoices.checkedRadioButtonId) {
                R.id.rbFirstChoice -> validate(rbFirstChoice)
                R.id.rbSecondChoice -> validate(rbSecondChoice)
                R.id.rbThirdChoice -> validate(rbThirdChoice)
            }
        }
    }

    private fun validate(choice: RadioButton) {
        if (currentQuestion <= questions.size - 1) {
            if (choice.text == correctAnswer) {
                setQuestions()
                Toast.makeText(requireContext(), getString(R.string.correct_answer), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show()
            }
        } else {

            Toast.makeText(requireContext(), getString(R.string.quiz_completed), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.homeFragment)
        }
    }

    private fun observeQuestions() {
        viewModel.getQuestions()

        viewModel.questions.observe(viewLifecycleOwner, Observer {
            questions = it
            setQuestions()
        })
    }

    private fun setQuestions() {
        tvCurrentQuestion.text =
            getString(R.string.tv_current_question, currentQuestion + 1, questions.size)
        tvQuestion.text = questions[currentQuestion].question
        rbFirstChoice.text = questions[currentQuestion].choices[0]
        rbSecondChoice.text = questions[currentQuestion].choices[1]
        rbThirdChoice.text = questions[currentQuestion].choices[2]
        correctAnswer = questions[currentQuestion].correctAnswer
        currentQuestion++
    }
}