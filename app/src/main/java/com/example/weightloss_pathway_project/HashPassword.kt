package com.example.weightloss_pathway_project

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

class HashPassword {
    // Attribute password for HashPassword Class
    private var password : String = ""
    // Attribute hash for HashPassword Class
    private var hash : String = ""

    // For type can use "MD5" or "SHA-1" etc
    fun hashingPassword(type : String, newAccount : HashPassword) : HashPassword{
        // converting inputted password to byte datatype array
        val bytes = MessageDigest
            .getInstance(type)
            .digest(newAccount.password.toByteArray())
        // return new hashed password as savable, encrypted string
        hash = DatatypeConverter.printHexBinary(bytes).uppercase()
        return newAccount
    }


}