package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateUserCredentials : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_credentials)

        val finish = findViewById<Button>(R.id.createUserFinishBtn)
        val cancel = findViewById<Button>(R.id.createUserCancelBtn)

        finish.setOnClickListener{
            finishedCreatedActivity(R.layout.activity_login)
        }

        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }
    }

    private fun finishedCreatedActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}