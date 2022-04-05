package com.example.weightloss_pathway_project

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_weekly.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class Weekly : AppCompatActivity() {
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
    private lateinit var menu: Button
    private lateinit var dateSelection: TextView
    private lateinit var date : Button
    private lateinit var dayOfWeek : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly)
        title = "Set Goals"

        database = Firebase.database.reference

        instantiate()
        createAdapters()
        onClick()
    }

    // Instantiate objects from XML and list
    private fun instantiate(){
        submit = findViewById(R.id.weeklySubmitBtn)
        menu = findViewById(R.id.weeklyMenuBtn)
        mode = findViewById(R.id.weeklyModeSpinner)
        intensity = findViewById(R.id.weeklyIntensitySpinner)
        duration = findViewById(R.id.weeklyDurationSpinner)
        nutritionGoal1 = findViewById(R.id.nutritionGoal1Txt)
        nutritionGoal2 = findViewById(R.id.nutritionGoal2Txt)
        nutritionGoal3 = findViewById(R.id.nutritionGoal3Txt)
        nutritionGoal4 = findViewById(R.id.nutritionGoal4Txt)
        dateSelection = findViewById(R.id.weeklyTabDateTxt)
        date = findViewById(R.id.weeklyTabDateBtn)

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
            try {
                if (modeSelection.isNotEmpty() && durationSelection.isNotEmpty() && intensitySelecion.isNotEmpty()) {
                    writeNewFitnessGoal(
                        modeSelection,
                        durationSelection,
                        intensitySelecion,
                        dateSelection.text.toString(),
                        dayOfWeek
                    )
                }

                for (goalValue: TextView in iteratedGoals) {
                    if (goalValue.text.isNotEmpty()) {
                        addedGoals.add(goalValue.text.toString())
                    }
                }

                for (goalValue: String in addedGoals) {
                    writeNewNutritionGoal(goalValue, dateSelection.text.toString(), dayOfWeek)
                }

                Toast.makeText(this, "Goal Created Successfully", Toast.LENGTH_SHORT).show()
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
    }


    // Class constructor for data input for new Account creation
    @IgnoreExtraProperties
    data class SaveFitnessGoal(val mode: String? = null, val duration: String? = null, val intensity: String? = null, val date : String? = null, val dow : String? ) {
        // Null default values create a no-argument default constructor, which is needed
    }

    // Write new Account to database with username as userId
    fun writeNewFitnessGoal(mode: String?, duration: String?, intensity: String?, date: String?, dow : String?){
        val goal = SaveFitnessGoal(mode, duration, intensity, date, dow)
        database.child("users").child(FirebaseAuth.getInstance().getCurrentUser()!!.getUid()).child("fitness").push().setValue(goal)
    }

    // Class constructor for data input for new Account creation
    @IgnoreExtraProperties
    data class SaveNutritionGoal(val nutritionalGoal: String? = null, val date : String? = null, val dow: String? ) {
        // Null default values create a no-argument default constructor, which is needed
    }

    // Write new Account to database with username as userId
    fun writeNewNutritionGoal(nutritionalGoal: String?, date: String?, dow: String?){
        val newGoal = SaveNutritionGoal(nutritionalGoal, date, dow)
        database.child("users").child(FirebaseAuth.getInstance().getCurrentUser()!!.getUid()).child("nutrition").push().setValue(newGoal)
    }

}
