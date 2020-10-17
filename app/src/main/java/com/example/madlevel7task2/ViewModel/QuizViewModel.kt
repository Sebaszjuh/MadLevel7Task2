package com.example.madlevel7task2.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel7task2.Model.Quiz
import com.example.madlevel7task2.Repository.QuizRepository
import kotlinx.coroutines.launch

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "FIRESTORE"
    private val questionRepository: QuizRepository = QuizRepository()

    val questions: LiveData<ArrayList<Quiz>> = questionRepository.quizzes

    fun getQuestions() {
        viewModelScope.launch {
            try {
                questionRepository.getQuiz()
            } catch (ex: QuizRepository.QuizRetrievalError) {
                val errorMsg = "Something went wrong while retrieving the Quiz"
                Log.e(TAG, ex.message ?: errorMsg)
            }
        }
    }
}