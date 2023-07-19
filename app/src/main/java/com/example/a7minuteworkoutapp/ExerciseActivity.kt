package com.example.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkoutapp.databinding.ActivityExerciseBinding
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {
    private var binding: ActivityExerciseBinding? = null
    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolBarExercise)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }

        resetTimer()
    }

    private fun resetTimer(){
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }
        setUpTimer()
    }

    private fun resetExerciseTimer(){
        binding?.tvStart?.text = "Exercise Name"
        binding?.flProgressBarExercise?.visibility = View.VISIBLE
        binding?.flProgressBar?.visibility = View.INVISIBLE
        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        setUpExerciseTimer()
    }

    private fun setUpTimer(){
        binding?.progressBar?.progress = restProgress
        restTimer = object: CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10-restProgress
                binding?.tvTimer?.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"Finished Timer",Toast.LENGTH_LONG).show()
                resetExerciseTimer()
            }
        }.start()
    }

    private fun setUpExerciseTimer(){
        binding?.progressBarExercise?.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30-exerciseProgress
                binding?.tvTimerExercise?.text = (30-exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"Finished Timer",Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }
        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        binding = null
    }

}