package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateUserContactInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_contact_info)

        val next = findViewById<Button>(R.id.createUserContactNextBtn)
        val cancel = findViewById<Button>(R.id.createUserCancelBtn)

        next.setOnClickListener{
            credentialsActivity(R.layout.activity_create_user_credentials)
        }

        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }
    }

    private fun credentialsActivity(view: Int){
        val intent = Intent(this, CreateUserCredentials::class.java)
        startActivity(intent)
    }

    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}