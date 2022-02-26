package com.example.weightloss_pathway_project

import android.telephony.PhoneNumberUtils.isGlobalPhoneNumber

class Phone {
    var area : String = ""
    var prefix : String = ""
    var lineNum : String = ""
    var phoneNumber : String = ""
    var isNumber : Boolean = false

    fun isValidPhone(phone : String) : Boolean {
        return isGlobalPhoneNumber(phone)
    }

    fun phoneToString() : String {
        return String.format("$(area)$prefix-$lineNum")
    }

    fun phoneBreakDown(phone : String) {
        area = phone.slice(0..2).toString()
        prefix = phone.slice(3..5)
        lineNum = phone.slice((6..9))
    }
}