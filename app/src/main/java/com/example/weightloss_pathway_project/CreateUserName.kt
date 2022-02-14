package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CreateUserName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_name)

        val firstname = findViewById<TextView>(R.id.createFirstNameTxt)
        val lastname = findViewById<TextView>(R.id.createLastNameTxt)

        // link next button
        val next = findViewById<Button>(R.id.createUserNameNextBtn)
        // link cancel button
        val cancel = findViewById<Button>(R.id.createUserCancelBtn)

        // create action when next button pressed
        next.setOnClickListener{
            contactInfoActivity(R.layout.activity_create_user_name)
        }

        // create action when cancel button pressed
        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }
    }

    // Intent that will open contact info activity when activated
    private fun contactInfoActivity(view: Int){
        val intent = Intent(this, CreateUserContactInfo::class.java)
        startActivity(intent)
    }

    // Intent that will open login activity when activated
    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}