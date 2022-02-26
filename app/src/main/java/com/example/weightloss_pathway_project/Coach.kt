package com.example.weightloss_pathway_project

import java.io.Serializable

class Coach : Account(), Serializable{
    var isAdmin : Boolean = true
}