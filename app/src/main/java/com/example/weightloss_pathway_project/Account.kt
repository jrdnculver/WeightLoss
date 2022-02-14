package com.example.weightloss_pathway_project

open class Account {
    var firstName : String = ""
    var lastName : String = ""
    var address : Address = Address()
    var phone : Phone = Phone()
    var isAdmin : Boolean = false
    var birthday : BirthDate = BirthDate()
    var title : String = ""
    var username : String = ""
    var password : String = ""

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
}

