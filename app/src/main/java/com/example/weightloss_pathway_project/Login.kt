package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var signIn = findViewById<Button>(R.id.loginSignInBtn)
        var createAccount = findViewById<Button>(R.id.loginCreateAccountBtn)

        signIn.setOnClickListener{
            mainActivity(R.layout.activity_main)
        }

        createAccount.setOnClickListener{
            createNameActivity(R.layout.activity_create_user_name)
        }

    }

    private fun mainActivity(view: Int){
        val intent = Intent(this, Main::class.java)
        startActivity(intent)
    }

    private fun createNameActivity(view: Int){
        val intent = Intent(this, CreateUserName::class.java)
        startActivity(intent)
    }
}