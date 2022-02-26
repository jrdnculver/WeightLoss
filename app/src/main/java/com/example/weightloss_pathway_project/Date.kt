package com.example.weightloss_pathway_project

class Date {
    var month : String = ""
    var day : String = ""
    var year : String = ""

    fun birthdayToString(birthday : Date) : String{
        return String.format("${month + 1} / $day / $year")
    }
}