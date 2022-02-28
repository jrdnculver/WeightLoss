package com.example.weightloss_pathway_project

class Date {
    var month : String = String()
    var day : String = String()
    var year : String = String()

    fun birthdayToString(birthday : Date) : String{
        return String.format("${month + 1} / $day / $year")
    }
}