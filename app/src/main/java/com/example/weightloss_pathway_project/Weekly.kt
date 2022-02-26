package com.example.weightloss_pathway_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class Weekly : AppCompatActivity() {
    private lateinit var modeSelection : String
    private lateinit var intensitySelecion : String
    private lateinit var durationSelection : String
    private lateinit var mode : Spinner
    private lateinit var intensity : Spinner
    private lateinit var duration : Spinner
    private lateinit var modes : ArrayList<String>
    private lateinit var intensities : ArrayList<String>
    private lateinit var durations : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly)
        title = "Set Goals"

        instantiate()
        createAdapters()
    }

    // Instantiate objects from XML and list
    private fun instantiate(){
        mode = findViewById<Spinner>(R.id.weeklyModeSpinner)
        intensity = findViewById<Spinner>(R.id.weeklyIntensitySpinner)
        duration = findViewById<Spinner>(R.id.weeklyDurationSpinner)

        modes = ArrayList(mutableListOf("Mode"))
        intensities = ArrayList(mutableListOf("Intensity", "Low", "Moderate", "High"))
        durations = ArrayList(mutableListOf("Duration", "10", "20", "30", "40", "50", "60", "70", "80", "90"))
    }

    private fun createAdapters(){
        // Create an ArrayAdapter using the string array and a default spinner layout

        val modeAdapter = ArrayAdapter(this,
            R.layout.spinnerlist, modes)
        mode.adapter = modeAdapter

        mode.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                modeSelection = modes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }

        // Create an ArrayAdapter using the string array and a default spinner layout

        val intensityAdapter = ArrayAdapter(this,
            R.layout.spinnerlist, intensities)
        intensity.adapter = intensityAdapter

        intensity.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                intensitySelecion = modes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        val durationAdapter = ArrayAdapter(this,
            R.layout.spinnerlist, durations)
        duration.adapter = durationAdapter

        duration.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                durationSelection = modes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

}
