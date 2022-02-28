package com.example.weightloss_pathway_project

import android.telephony.PhoneNumberUtils.isGlobalPhoneNumber

class Phone {
    var area : String = String()
    var prefix : String = String()
    var lineNum : String = String()
    var phoneNumber : String = String()
    var isNumber : Boolean = false

    fun isValidPhone(phone : String) : Boolean {
        return isGlobalPhoneNumber(phone)
    }

    fun phoneToString() : String {
        return String.format("($area)$prefix-$lineNum")
    }

    fun phoneBreakDown(phone : String) {
        area = phone.slice(0..2).toString()
        prefix = phone.slice(3..5)
        lineNum = phone.slice((6..9))
    }

    fun phoneToDatabaseFire() : String {
        return String.format("$area/$prefix/$lineNum")
    }
}