package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.QuizLabelBinding

class QuizAdapter(private val questions: List<Quiz>) : RecyclerView.Adapter<QuizAdapter.ViewHolder>(){

    inner class ViewHolder(questionView: View) : RecyclerView.ViewHolder(questionView) {

        val binding = QuizLabelBinding.bind(questionView)

        fun databind(quiz: Quiz) {
            binding.tvQuestions.text = quiz.question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.quiz_label,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(questions[position])

    }

    override fun getItemCount(): Int {
        return questions.size
    }


}