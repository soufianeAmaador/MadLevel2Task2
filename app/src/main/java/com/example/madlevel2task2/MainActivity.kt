package com.example.madlevel2task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.quiz_label.*
import kotlinx.android.synthetic.main.quiz_label.view.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Quiz>()
    private val quizAdapter = QuizAdapter(questions)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.rvQuiz.layoutManager = LinearLayoutManager(
            this@MainActivity,
            RecyclerView.VERTICAL, false
        )
        binding.rvQuiz.adapter = quizAdapter

        questions.add(Quiz("A 'val' and 'var' are the same.", 0))
        questions.add(Quiz("Mobile Application Development grants 12 ECTS.", 0))
        questions.add(Quiz("A Unit in Kotlin corresponds to a void in Java.", 1))
        questions.add(Quiz("In Kotlin 'when' replaces the 'switch' operator in Java.", 1))

        binding.rvQuiz.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        createItemTouchHelper().attachToRecyclerView(rvQuiz)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.RIGHT) {
                    if (questions[position].answers == 1) {

                        questions.removeAt(position)

                    } else if (questions[position].answers == 0) {

                        Snackbar.make(rvQuiz, "Thats not the right answer", Snackbar.LENGTH_SHORT)
                            .show()

                    }

                } else if (direction == ItemTouchHelper.LEFT) {

                    if (questions[position].answers == 1) {

                        Snackbar.make(rvQuiz, "Thats not the right answer", Snackbar.LENGTH_SHORT)
                            .show()
                        quizAdapter.notifyDataSetChanged()

                    } else if (questions[position].answers == 0) {

                        questions.removeAt(position)
                    }
                }

                quizAdapter.notifyDataSetChanged()

            }
        }

        return ItemTouchHelper(callBack)
    }
}