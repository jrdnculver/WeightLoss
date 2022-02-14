package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // link sign in button
        var signIn = findViewById<Button>(R.id.loginSignInBtn)
        // link create account button
        var createAccount = findViewById<Button>(R.id.loginCreateAccountBtn)

        // create action when sign button pressed
        signIn.setOnClickListener{
            mainActivity(R.layout.activity_main)
        }

        // create action when createAccount button pressed
        createAccount.setOnClickListener{
            createNameActivity(R.layout.activity_create_user_name)
        }

    }

    // Intent that will open main activity when activated
    private fun mainActivity(view: Int){
        val intent = Intent(this, Main::class.java)
        startActivity(intent)
    }

    // Intent that will open create user activity when activated
    private fun createNameActivity(view: Int){
        val intent = Intent(this, CreateUserName::class.java)
        startActivity(intent)
    }
}