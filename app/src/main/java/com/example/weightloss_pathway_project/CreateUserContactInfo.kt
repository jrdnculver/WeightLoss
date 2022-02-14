package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateUserContactInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_contact_info)

        // link next button
        val next = findViewById<Button>(R.id.createUserContactNextBtn)
        // link cancel button
        val cancel = findViewById<Button>(R.id.createUserCancelBtn)

        // create action when next button pressed
        next.setOnClickListener{
            credentialsActivity(R.layout.activity_create_user_credentials)
        }

        // create action when cancel button pressed
        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }
    }

    // Intent that will open credentials activity when activated
    private fun credentialsActivity(view: Int){
        val intent = Intent(this, CreateUserCredentials::class.java)
        startActivity(intent)
    }

    // Intent that will open login activity when activated
    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}