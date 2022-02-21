package com.example.weightloss_pathway_project

class BirthDate {
    var month : String = ""
    var day : String = ""
    var year : String = ""

    fun birthdayToString(birthday : BirthDate) : String{
        return String.format("${month + 1} / $day / $year")
    }
}