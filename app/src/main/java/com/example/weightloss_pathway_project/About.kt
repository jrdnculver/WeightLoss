package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setTheme(R.style.WeightLoss_main_style)
        title = "About Us!"

        // link menu button
        val menu = findViewById<Button>(R.id.aboutMenuBtn)

        // create action when menu button pressed
        menu.setOnClickListener{
            mainActivity(R.layout.activity_main)
        }
    }

    // Intent that will open main activity when activated
    private fun mainActivity(view: Int){
        val intent = Intent(this, Main::class.java)
        startActivity(intent)
    }

}