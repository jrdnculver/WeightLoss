package com.example.weightloss_pathway_project

import java.io.Serializable

class NutritionalGoals : Serializable, DefinedGoal(){

    fun goalToString() : String{
        return String.format("$goal")
    }
}