package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateUserName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_name)

        val next = findViewById<Button>(R.id.createUserNameNextBtn)
        val cancel = findViewById<Button>(R.id.createUserCancelBtn)

        next.setOnClickListener{
            contactInfoActivity(R.layout.activity_create_user_name)
        }

        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }
    }

    private fun contactInfoActivity(view: Int){
        val intent = Intent(this, CreateUserContactInfo::class.java)
        startActivity(intent)
    }

    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}