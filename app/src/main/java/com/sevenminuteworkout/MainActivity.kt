package com.sevenminuteworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        llStart.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        llBMI.setOnClickListener {
            // starten der BMI Activity
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }

        llHistory.setOnClickListener {
            // starten der History Activity
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        // starten der Month Activity

        llMonthChallenge.setOnClickListener {
            val intent = Intent(this, MonthActivity::class.java)
            startActivity(intent)
        }
    }
}