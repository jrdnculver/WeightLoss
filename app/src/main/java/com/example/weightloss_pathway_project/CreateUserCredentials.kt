package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateUserCredentials : AppCompatActivity() {
    private lateinit var creatingUser : Client
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Account Creation"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_credentials)

        // Access previous user account
        creatingUser = getAccessToCurrentUser()
        // link finish button
        val finish = findViewById<Button>(R.id.createUserFinishBtn)
        // link cancel button
        val cancel = findViewById<Button>(R.id.createUserCancelBtn)

        // create action when finish button pressed
        finish.setOnClickListener{
            finishedCreatedActivity(R.layout.activity_login)
        }
        // create action when cancel button pressed
        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }
    }

    // Intent that will open login activity when activated
    private fun finishedCreatedActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        intent.putExtra("user", creatingUser)
        startActivity(intent)
    }

    // Intent that will open login activity when activated
    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    // Access Intent object from previous activity
    private fun getAccessToCurrentUser(): Client {
        return intent.extras?.get("user") as Client

    }
}