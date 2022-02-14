package com.example.weightloss_pathway_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class Main : AppCompatActivity() {
    private lateinit var myToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This value will be dynamic based on the user logged in
        val personName = "Jordan"
        val welcomeName = "Welcome, $personName"

        // Will be dynamic base on current Motivational Quote provided
        val welcomeMessage = findViewById<TextView>(R.id.mainWelcomeMessage)
        welcomeMessage.text = welcomeName

        // link set new goals button
        val newGoals = findViewById<Button>(R.id.mainSetGoalsBtn)

        // create action when newGoals button pressed
        newGoals.setOnClickListener{
            setWeeklyActivity(R.layout.activity_weekly)
        }

        // gets id's of UI components for modification
        val drawerLayout: DrawerLayout = findViewById(R.id.mainNavDrawer)
        val nav: NavigationView = findViewById(R.id.navView)

        myToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(myToggle)

        myToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // provides functionality whenever item clicked on navigation bar in main menu
        nav.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dailyMonday -> {
                    Snackbar.make(findViewById(R.id.dailyMonday), "We are going to Monday",Snackbar.LENGTH_LONG).show()
                }
                R.id.dailyTuesday -> {
                    Snackbar.make(findViewById(R.id.dailyTuesday), "We are going to Tuesday",Snackbar.LENGTH_LONG).show()
                }
                R.id.dailyWednesday -> {
                    Snackbar.make(findViewById(R.id.dailyWednesday), "We are going to Wednesday",Snackbar.LENGTH_LONG).show()
                }
                R.id.dailyThursday -> {
                    Snackbar.make(findViewById(R.id.dailyThursday), "We are going to Thursday",Snackbar.LENGTH_LONG).show()
                }
                R.id.dailyFriday -> {
                    Snackbar.make(findViewById(R.id.dailyFriday), "We are going to Friday",Snackbar.LENGTH_LONG).show()
                }
                R.id.dailySaturday -> {
                    Snackbar.make(findViewById(R.id.dailySaturday), "We are going to Saturday",Snackbar.LENGTH_LONG).show()
                }
                R.id.dailySunday -> {
                    Snackbar.make(findViewById(R.id.dailySunday), "We are going to Sunday",Snackbar.LENGTH_LONG).show()
                }
                R.id.navSettings -> {
                    Snackbar.make(findViewById(R.id.navSettings), "Going to settings",Snackbar.LENGTH_LONG).show()
                }
                R.id.navContactUs -> {
                    contactUsActivity(R.layout.activity_fire_base)
                }
                R.id.navAbout -> {
                    aboutActivity(R.layout.activity_about)
                }
                R.id.navSignOut -> {
                    Snackbar.make(findViewById(R.id.navSignOut), "Do you want to Sign out?",Snackbar.LENGTH_LONG).show()
                    loginActivity(R.layout.activity_login)

                }

            }
            true}


    }

    // Intent that will open login activity when activated
    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    // Intent that will open about activity when activated
    private fun aboutActivity(view: Int){
        val intent = Intent(this, About::class.java)
        startActivity(intent)
    }

    // Intent that will open weekly activity when activated
    private fun setWeeklyActivity(view: Int){
        val intent = Intent(this, Weekly::class.java)
        startActivity(intent)
    }

    // Intent that will open contact us activity when activated
    private fun contactUsActivity(view: Int){
        val intent = Intent(this, FireBase::class.java)
        startActivity(intent)
    }

    // important for navigation UI purposes
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (myToggle.onOptionsItemSelected(item)) {return true}
        return super.onOptionsItemSelected(item)
    }
}