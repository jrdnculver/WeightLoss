package com.example.weightloss_pathway_project

import java.io.Serializable

class FitnessGoals : Serializable {
    var dayOfWeek : String = String()
    var mode : String = String()
    var intensity : String = String()
    var duration : String = String()
    var date : String = String()

    fun goalString() : String{
        return String.format("$mode for $duration minutes at $intensity intensity")
    }
}