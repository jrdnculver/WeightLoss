package com.example.weightloss_pathway_project

import java.util.regex.Pattern

class Email {
    var userEmail : String = String()
    var isEmail : Boolean = false

    // Check for valid email
    fun isValidEmail(email: String): Boolean {
        val EMAIL_STRING = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
         isEmail = Pattern.compile(EMAIL_STRING).matcher(email).matches()

        if (isEmail == true){
            userEmail = email
            return true
        }
        else{
            return false
        }
    }
}