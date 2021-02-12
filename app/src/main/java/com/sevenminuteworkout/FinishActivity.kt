package com.sevenminuteworkout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        setSupportActionBar(toolbar_finish_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_finish_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnFinish.setOnClickListener {
           finish()
        }

        addDateToDatabase()
    }


    private fun addDateToDatabase() {

        val c = Calendar.getInstance() // Kalender Instanz
        val dateTime = c.time // Aktuelle Uhrzeit des Telefons.
        Log.e("Date : ", "" + dateTime)


        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) // Datum formatieren
        val date = sdf.format(dateTime)
        Log.e("Formatted Date : ", "" + date) // Formatted date is printed in the log.

        // Instance of the Sqlite Open Helper class.
        val dbHandler = SqliteOpenHelper(this, null)
        dbHandler.addDate(date) // Add date funktion von der SqliteOpenHelper Klasse
        Log.e("Date : ", "Added...")
    }
}