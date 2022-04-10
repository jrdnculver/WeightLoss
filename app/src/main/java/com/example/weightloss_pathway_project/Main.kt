package com.example.weightloss_pathway_project

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Main : AppCompatActivity() {
    private lateinit var myToggle: ActionBarDrawerToggle
    private var currentUser : Client? = null
    private var firebaseUser : FirebaseUser? = null
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting access to current user
        firebaseUser = FirebaseAuth.getInstance().currentUser
        loggedIn()

        // link set new goals button
        val newGoals = findViewById<Button>(R.id.mainSetGoalsBtn)

        // create action when newGoals button pressed
        newGoals.setOnClickListener{
            setWeeklyActivity(R.layout.activity_created_goal_weekly)
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
                R.id.dailyViewGoals -> {
                    weeklyTabActivity(R.layout.activity_weekly_tab)
                }
                R.id.navSettings -> {
                    settingsActivity(R.layout.activity_settings)
                }
                R.id.navContactUs -> {
                    contactUsActivity(R.layout.activity_fire_base)
                }
                R.id.navAbout -> {
                    aboutActivity(R.layout.activity_about)
                }
                R.id.navHelp -> {
                    helpActivity(R.layout.activity_help)
                }
                R.id.navSignOut -> {
                    Snackbar.make(findViewById(R.id.navSignOut), "Do you want to Sign out?",Snackbar.LENGTH_LONG).show()
                    Firebase.auth.signOut()
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
        val intent = Intent(this, SelectedGoalWeekly::class.java)
        startActivity(intent)
    }

    // Intent that will open contact us activity when activated
    private fun contactUsActivity(view: Int){
        val intent = Intent(this, FireBase::class.java)
        startActivity(intent)
    }

    // Intent that will open WeeklyTab activity when activated
    private fun weeklyTabActivity(view: Int){
        val intent = Intent(this, WeeklyTab::class.java)
        startActivity(intent)
    }

    // Intent that will open login activity when activated
    private fun settingsActivity(view: Int){
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    // Intent that will open login activity when activated
    private fun helpActivity(view: Int){
        val intent = Intent(this, Help::class.java)
        startActivity(intent)
    }

    // important for navigation UI purposes
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (myToggle.onOptionsItemSelected(item)) {return true}
        return super.onOptionsItemSelected(item)
    }

    private fun loggedIn(){
        // Access Database
        database = Firebase.database.reference.child("users").child(firebaseUser!!.uid).child("account")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                currentUser = dataSnapshot.getValue<Client?>()

                // This value will be dynamic based on the user logged in
                val welcomeName = "Welcome, ${currentUser?.firstname}"

                // Will be dynamic base on current Motivational Quote provided
                val welcomeMessage = findViewById<TextView>(R.id.mainWelcomeMessage)
                welcomeMessage.text = welcomeName
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)
    }
}