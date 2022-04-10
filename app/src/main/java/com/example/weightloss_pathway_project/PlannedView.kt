package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MultiAutoCompleteTextView

class PlannedView : AppCompatActivity() {
    private lateinit var planToComplete : MultiAutoCompleteTextView
    private lateinit var planToOvercome : MultiAutoCompleteTextView
    private lateinit var back : Button
    private lateinit var viewGoal : DefinedGoal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planned_view)

        initialize()
        onClick()
        try{
            getPlans()
        }
        catch (e : Exception){
            ;
        }

        setValues()
    }

    fun initialize(){
        planToComplete = findViewById(R.id.planCompleteGoalViewTxt)
        planToOvercome = findViewById(R.id.planToOvercomeViewTxt)
        back = findViewById(R.id.plannedBackViewBtn)
        viewGoal = DefinedGoal()

        viewGoal = getPlans()
    }

    fun getPlans() : DefinedGoal{
        return intent.extras?.get("viewGoal") as DefinedGoal
    }

    fun setValues(){
        planToComplete.setText(viewGoal.planToComplete)
        planToOvercome.setText(viewGoal.planToOvercome)
    }

    fun onClick(){
        back.setOnClickListener{
            weeklyTabActivity(R.layout.activity_weekly_tab)
        }
    }

    // Intent that will open main activity when activated
    private fun weeklyTabActivity(view: Int){
        val intent = Intent(this, WeeklyTab::class.java)
        startActivity(intent)
    }
}