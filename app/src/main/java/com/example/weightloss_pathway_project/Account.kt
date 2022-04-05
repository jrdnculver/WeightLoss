package com.example.weightloss_pathway_project

import java.io.Serializable

open class Account : Serializable{
    var firstname : String = String()
    var lastname : String = String()
    var address : String = String()
    var email : String = String()
    var phone : String = String()
    var birthday : String = String()
    var password : String = String()

    fun checkName(first: String) : String {
        // New mutable list for modified strings
        var newSplit : ArrayList<String> = ArrayList<String>()
        // Will run if first is not null
        if (first != "") {
            // Separate each value with by delimiter space
            var splitting = first.split(" ")
            // Capitalize for letter in each word
            for (sp in splitting){
                var upper = sp.replaceFirstChar { sp[0].uppercase() }
                // Add word to Array list "newSplit"
                newSplit.add(upper)
            }
        }
        // Return the arraylist to a string and trim white space
        return newSplit.joinToString(" ").trim()
    }

    fun checkMonth(date: String) : String{
        var month : String = ""

        // New mutable list for modified strings
        var newSplit : ArrayList<String> = ArrayList<String>()
        if (date != "") {
            // Separate each value with by delimiter space
            var splitting = date.split("/")
                month = splitting[0]
        }

        // Get length of day variable
        var num = month.trim().length
        // Ensure formatting for month value
        if (num == 1){
            var zero : String = "0".trim()
            month = zero.plus(month.trim())
        }

        return month
    }

    fun checkDay(date: String) : String{
        var day : String = ""

        if (date != "") {
            // Separate each value with by delimiter space
            var splitting = date.split("/")
            day = splitting[1]
        }

        // Get length of day variable
        var num = day.trim().length
        // Ensure formatting for day value
        if (num == 1){
            var zero : String = "0".trim()
            day = zero.plus(day.trim())
        }

        return day
    }

    fun checkYear(date: String) : String{
        var year : String = ""

        if (date != "") {
            // Separate each value with by delimiter space
            var splitting = date.split("/")
            year = splitting[2]
        }

        return year
    }
}

