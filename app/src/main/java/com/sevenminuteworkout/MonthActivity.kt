package com.sevenminuteworkout

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_month.*
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.prefs.AbstractPreferences
import android.preference.PreferenceManager as PreferenceManager

class MonthActivity : AppCompatActivity() {

    private var startButtonAppereance = true

    lateinit var sharedPreferences: SharedPreferences
    var isStarted = false


    private var progressBar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_month)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isStarted = sharedPreferences.getBoolean("Button", false)

        setSupportActionBar(toolbar_month_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Monats Challenge"

        toolbar_month_activity.setNavigationOnClickListener {
            onBackPressed()
        }


        startChallengeBtn.setOnClickListener{
            val currrentDate = getActualDate()

            val dbHandler = SqliteOpenHelper(this, null)

            if(dbHandler.checkTrainingcurrentDate(currrentDate)){
                val editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("STARTDATE", currrentDate)
                editor.apply()
                Toast.makeText(this, "Starttag gespeichert", Toast.LENGTH_LONG).show()
                //Mach dem druecken soll der Button verschwinden
                startButtonAppereance = false
                startChallengeBtn.isEnabled = false

            }
            else{
                Toast.makeText(this, "Trainiere Heute und dann komm wieder zurueck", Toast.LENGTH_LONG).show()
            }

        }

        resetChallengeBtn.setOnClickListener{
            resetMonthChallengeButton()
        }





    }

    // Das muss noch implementiert werden

    fun passDaysTo(){

    }



    fun getActualDate():String{
        val c = Calendar.getInstance() // Kalender Instanz
        val dateTime = c.time // Aktuelle Uhrzeit des Telefons.
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) // Datum formatieren
        val date = sdf.format(dateTime)

        return date
    }






    fun getCompletedDatesInARow(): Int {
        val dbHandler = SqliteOpenHelper(this, null)
        val allCompletedDatesList =
                dbHandler.getAllCompletedDatesList()
        var datesListInRow = mutableListOf<String>()
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        for (element in allCompletedDatesList) {
            datesListInRow.add(sdf.format(element))
        }

        return 1
    }
    private fun resetMonthChallengeButton() {
        val customDialog = Dialog(this)

        customDialog.setContentView(R.layout.reset_month_challenge)

        customDialog.tvYes.setOnClickListener {
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            finish()
            startChallengeBtn.isEnabled = true
            customDialog.dismiss() // Dialog wird ausgeblendet
        }
        customDialog.tvNo.setOnClickListener {
            customDialog.dismiss()
        }
        //Dialog Fenster wird angezeigt
        customDialog.show()
    }
}

