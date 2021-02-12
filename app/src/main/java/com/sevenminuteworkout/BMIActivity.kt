package com.sevenminuteworkout

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bmi.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        //private const val US_UNITS_VIEW = "US_UNIT_VIEW" // !!! noch implementieren
    }
    private var currentVisibleView: String = METRIC_UNITS_VIEW // varianle zum ANzeigen der aktuellen VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        setSupportActionBar(toolbar_bmi_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //set back button
        supportActionBar?.title = "Berechne deinen BMI" // Setting an title in the action bar.

        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        // aktuell nur Einheiten, US Einheiten müssen noch implementiert werden
        /*rgUnits.setOnCheckedChangeListener { radioGroup: RadioGroup, checkedId: Int ->

            // Here is the checkId is METRIC UNITS view then make the view visible else US UNITS view.
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUsUnitsView()
            }
        }
        */



        btnCalculateUnits.setOnClickListener {

            if (currentVisibleView.equals(METRIC_UNITS_VIEW)) {
                // validierung
                if (validateMetricUnits()) {

                    // Typecasting zu Float und Umrechnung in Meter
                    val heightValue: Float = etMetricUnitHeight.text.toString().toFloat() / 100

                    // Typecasting
                    val weightValue: Float = etMetricUnitWeight.text.toString().toFloat()

                    // BMI Formel
                    val bmi = weightValue / (heightValue * heightValue)

                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(
                            this@BMIActivity,
                            "Bitte gebe richtige Werte ein",
                            Toast.LENGTH_SHORT
                    )
                            .show()
                }
            } else {
                Toast.makeText(
                        this@BMIActivity,
                        "Bitte gebe Richtige Einheiten ein",
                        Toast.LENGTH_SHORT
                )
                        .show()
            }
        }

    }


        private fun validateMetricUnits(): Boolean {
            var isValid = true

            if (etMetricUnitWeight.text.toString().isEmpty()) {
                isValid = false
            } else if (etMetricUnitHeight.text.toString().isEmpty()) {
                isValid = false
            }

            return isValid
        }

        /**
         * US EInheiten muessen noch als Radio Gruppe imolementiert werden
         */
        /*private fun validateUsUnits(): Boolean {
        var isValid = true

        if (etUsUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (etUsUnitHeightFeet.text.toString().isEmpty()) {
            isValid = false
        } else if (etUsUnitHeightInch.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }

     */

        /**
         * Anzeigen des BMI Ergebniss
         */
        private fun displayBMIResult(bmi: Float) {

            val bmiLabel: String
            val bmiDescription: String

            if (java.lang.Float.compare(bmi, 15f) <= 0) {
                bmiLabel = "Du bist extrem untergewichtig"
                bmiDescription = "Du solltest wirklich mehr auf dich achten und mehr essen"
            } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(
                            bmi,
                            16f
                    ) <= 0
            ) {
                bmiLabel = "Stark untergewichtig"
                bmiDescription = "Du solltest wirklich mehr auf dich achten und mehr essen"
            } else if (java.lang.Float.compare(bmi, 16f) > 0 && java.lang.Float.compare(
                            bmi,
                            18.5f
                    ) <= 0
            ) {
                bmiLabel = "Untergewichtig"
                bmiDescription = "Du solltest wirklich mehr auf dich achten und mehr essen"
            } else if (java.lang.Float.compare(bmi, 18.5f) > 0 && java.lang.Float.compare(
                            bmi,
                            25f
                    ) <= 0
            ) {
                bmiLabel = "Normal"
                bmiDescription = "Glückwunsch, Du bist gut in Form"
            } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                            bmi,
                            30f
                    ) <= 0
            ) {
                bmiLabel = "Übergewichtig"
                bmiDescription = "Du solltest wirklich mehr auf dich achten und weniger Essen sowie Sport machen!"
            } else if (java.lang.Float.compare(bmi, 30f) > 0 && java.lang.Float.compare(
                            bmi,
                            35f
                    ) <= 0
            ) {
                bmiLabel = "Starkes Übergewicht - Adipositas Klasse 1"
                bmiDescription = "Du bist in einer sehr schlechten Verfassung, achte auf dich und mach Sport!"
            } else if (java.lang.Float.compare(bmi, 35f) > 0 && java.lang.Float.compare(
                            bmi,
                            40f
                    ) <= 0
            ) {
                bmiLabel = "Starkes Übergewicht - Adipositas Klasse 2"
                bmiDescription = "Du bist in einer sehr schlechten Verfassung, achte auf dich und mach Sport!"
            } else {
                bmiLabel = "Starkes Übergewicht - Adipositas Klasse 3"
                bmiDescription = "Du bist in einer sehr schlechten Verfassung, achte auf dich und mach Sport!"
            }

            tvYourBMI.visibility = View.VISIBLE
            tvBMIValue.visibility = View.VISIBLE
            tvBMIType.visibility = View.VISIBLE
            tvBMIDescription.visibility = View.VISIBLE

            // Runden auf 2 Nachkommastellen
            val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

            tvBMIValue.text = bmiValue // Label wird zur Textview gesetzt
            tvBMIType.text = bmiLabel
            tvBMIDescription.text = bmiDescription
        }

    }
