package com.example.weightloss_pathway_project

import java.io.Serializable

class NutritionalGoals : Serializable{
    var nutritionalGoal : String = String()
    var dayOfWeek : String = String()
    var date : String = String()

    fun goalToString() : String{
        return String.format("$nutritionalGoal")
    }
}