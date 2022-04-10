package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PlanningGoals : AppCompatActivity() {
    private lateinit var back : Button
    private lateinit var submitPlan : Button
    private lateinit var completeGoal : MultiAutoCompleteTextView
    private lateinit var overComeObstacles : MultiAutoCompleteTextView
    private lateinit var selectedGoal : DefinedGoal
    private var firebaseUser : FirebaseUser? = null
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planning_goals)

        initialize()
        onClick()
    }

    fun initialize(){
        selectedGoal = getGoal()
        back = findViewById(R.id.plannedBackBtn)
        submitPlan = findViewById(R.id.plannedAddGoalBtn)
        completeGoal = findViewById(R.id.planCompleteGoalViewTxt)
        overComeObstacles = findViewById(R.id.planToOvercomeViewTxt)
        firebaseUser = FirebaseAuth.getInstance().currentUser
        database = Firebase.database.reference.child("users").child(firebaseUser!!.uid).child("plannedGoals")
    }

    fun getGoal() : DefinedGoal{
        return intent.extras?.get("definedGoal") as DefinedGoal
    }

    private fun backActivity(view: Int){
        val intent = Intent(this, SelectedGoalWeekly::class.java)
        startActivity(intent)
    }

    fun onClick(){
        back.setOnClickListener{
            backActivity(R.layout.activity_selected_goal_weekly)
        }

        submitPlan.setOnClickListener{
            selectedGoal.planToComplete = completeGoal.text.toString()
            selectedGoal.planToOvercome = overComeObstacles.text.toString()

            if(selectedGoal.planToComplete != "" && selectedGoal.planToOvercome != ""){
                writeNewGoal()
                Toast.makeText(this, "Goal Successfully Planned", Toast.LENGTH_SHORT).show()
                backActivity(R.layout.activity_selected_goal_weekly)
            }
            else{
                Toast.makeText(this, "Enter Valid Plans for the Goal", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // Write new Account to database with username as userId
    fun writeNewGoal(){
        database.push().setValue(selectedGoal)
    }
}