package com.example.weightloss_pathway_project

import java.io.Serializable

class Address : Serializable {
    var street = ""
    var city = ""
    var state = ""
    var zip = ""

    fun addressToString() : String {
        return String.format("$street $city, $state $zip")
    }
}

