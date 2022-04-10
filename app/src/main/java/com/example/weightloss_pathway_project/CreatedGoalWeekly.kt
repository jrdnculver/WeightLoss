package com.example.weightloss_pathway_project

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class CreatedGoalWeekly : AppCompatActivity() {
    private lateinit var modeSelection : String
    private lateinit var intensitySelecion : String
    private lateinit var durationSelection : String
    private lateinit var mode : Spinner
    private lateinit var intensity : Spinner
    private lateinit var duration : Spinner
    private lateinit var nutritionGoal1 : TextView
    private lateinit var nutritionGoal2 : TextView
    private lateinit var nutritionGoal3 : TextView
    private lateinit var nutritionGoal4 : TextView
    private lateinit var modes : ArrayList<String>
    private lateinit var intensities : ArrayList<String>
    private lateinit var durations : ArrayList<String>
    private lateinit var database: DatabaseReference
    private lateinit var submit: Button
    private lateinit var back: Button
    private lateinit var dateSelection: TextView
    private lateinit var date : Button
    private lateinit var dayOfWeek : String
    private lateinit var newFitGoal : FitnessGoals
    private lateinit var newNutGoal : NutritionalGoals

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_goal_weekly)
        title = "Set Goals"

        database = Firebase.database.reference

        instantiate()
        createAdapters()
        onClick()
    }

    // Instantiate objects from XML and list
    private fun instantiate(){
        submit = findViewById(R.id.weeklySubmitBtn)
        back = findViewById(R.id.weeklyMenuBtn)
        mode = findViewById(R.id.weeklyModeSpinner)
        intensity = findViewById(R.id.weeklyIntensitySpinner)
        duration = findViewById(R.id.weeklyDurationSpinner)
        nutritionGoal1 = findViewById(R.id.nutritionGoal1Txt)
        nutritionGoal2 = findViewById(R.id.nutritionGoal2Txt)
        nutritionGoal3 = findViewById(R.id.nutritionGoal3Txt)
        nutritionGoal4 = findViewById(R.id.nutritionGoal4Txt)
        dateSelection = findViewById(R.id.weeklyTabDateTxt)
        date = findViewById(R.id.weeklyTabDateBtn)
        newFitGoal = FitnessGoals()
        newNutGoal = NutritionalGoals()
        dayOfWeek = String()
        database = database.child("users").child(FirebaseAuth.getInstance().getCurrentUser()!!.getUid()).child("clientGoals")

        modes = ArrayList(mutableListOf("Cycling", "Running", "Treadmill", "Gym"))
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
                intensitySelecion = intensities[position]
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
                durationSelection = durations[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    // Functions for onClick methods
    private fun onClick() {
        submit.setOnClickListener() {
            val iteratedGoals = ArrayList<TextView>(
                mutableListOf(
                    nutritionGoal1,
                    nutritionGoal2,
                    nutritionGoal3,
                    nutritionGoal4
                )
            )
            val addedGoals = ArrayList<String>()

            newFitGoal.mode = modeSelection
            newFitGoal.intensity = intensitySelecion
            newFitGoal.duration = durationSelection
            newFitGoal.goal = newFitGoal.goalString()
            newFitGoal.dow = dayOfWeek
            newFitGoal.date = dateSelection.text.toString()
            try {
                if (modeSelection.isNotEmpty() && durationSelection.isNotEmpty() && intensitySelecion.isNotEmpty()) {
                    if (modeSelection != "Mode" && durationSelection != "Duration" && intensitySelecion != "Intensity"){
                        writeNewFitnessGoal()
                    }
                }

                for (goalValue: TextView in iteratedGoals) {
                    if (goalValue.text.isNotEmpty()) {
                        addedGoals.add(goalValue.text.toString())
                    }
                }

                for (goalValue: String in addedGoals) {
                    newNutGoal.goal = goalValue.trim()
                    newNutGoal.date = dateSelection.text.toString().trim()
                    newNutGoal.dow = dayOfWeek.trim()
                    writeNewNutritionGoal()
                }

                Toast.makeText(this, "Goal Created Successfully", Toast.LENGTH_SHORT).show()
                selectGoalActivity(R.layout.activity_selected_goal_weekly)

            } catch (e: Exception) {
                Toast.makeText(this, "Error Creating Goal", Toast.LENGTH_SHORT).show()
            }
        }

        val DOW = DayOfWeek()
        val DATE = Date()
        date.setOnClickListener {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val myDatePicker =
                DatePickerDialog(
                    this,
                    android.R.style.ThemeOverlay,
                    { _, Year, Month, Day ->
                        DOW.dd = Day
                        DOW.mm = Month + 1
                        DOW.yyyy = Year
                        dateSelection.text =
                            "${DATE.monthToString(Month + 1)} $Day, $Year, ${DOW.calculate()}"
                        dayOfWeek = DOW.calculate()
                    },
                    year,
                    month,
                    day
                )
            myDatePicker.show()
        }

        back.setOnClickListener{
            selectGoalActivity(R.layout.activity_selected_goal_weekly)
        }
    }

    // Write new Account to database with username as userId
    fun writeNewFitnessGoal(){
        database.push().setValue(newFitGoal)
    }

    // Write new Account to database with username as userId
    fun writeNewNutritionGoal(){
        database.push().setValue(newNutGoal)
    }

    // Intent that will open PlanningGoals activity when activated
    private fun selectGoalActivity(view: Int){
        val intent = Intent(this, SelectedGoalWeekly::class.java)
        startActivity(intent)
    }
}
