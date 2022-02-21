package com.example.weightloss_pathway_project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.widget.AdapterView
import android.widget.ArrayAdapter


class CreateUserContactInfo : AppCompatActivity() {
    private lateinit var creatingUser : Client
    private lateinit var states : MutableList<String>
    private var stateSelection : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_contact_info)
        title = "Account Creation"
        // Access previous information from first name, last name, and birthdate
        creatingUser = getAccessToCurrentUser()

        states = mutableListOf<String>("State", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI","ID",
                                                                "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS",
                                                                "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
                                                                "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
                                                                "WI", "WY")

        val street = findViewById<EditText>(R.id.createContactStreetTxt)
        val city = findViewById<EditText>(R.id.createContactCityTxt)
        val state = findViewById<Spinner>(R.id.createContactState)
        val phone = findViewById<EditText>(R.id.createContactPhoneTxt)
        val email = findViewById<EditText>(R.id.createContactEmailTxt)

        val zip = findViewById<EditText>(R.id.createContactZipTxt)

        // link next button
        val next = findViewById<Button>(R.id.createUserContactNextBtn)
        // link cancel button
        val cancel = findViewById<Button>(R.id.createUserCancelBtn)

// Create an ArrayAdapter using the string array and a default spinner layout
        if (state != null) {
            val adapter = ArrayAdapter(this,
                R.layout.spinnerlist, states)
            state.adapter = adapter

            state.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    stateSelection = states[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }




    // create action when next button pressed
        next.setOnClickListener{

            if (street.text.isEmpty()){
                Snackbar.make(findViewById(R.id.createContactStreetTxt), "Fill in Street", Snackbar.LENGTH_LONG).show()
            }
            else if (city.text.isEmpty()){
                Snackbar.make(findViewById(R.id.createContactCityTxt), "Fill in City", Snackbar.LENGTH_LONG).show()
            }
            else if (stateSelection == "" || stateSelection == "State"){
                Snackbar.make(findViewById(R.id.createContactState), "Select a State", Snackbar.LENGTH_LONG).show()
            }
            else if (zip.text.isEmpty()){
                Snackbar.make(findViewById(R.id.createContactZipTxt), "Fill in Zip Code", Snackbar.LENGTH_LONG).show()
            }
            else if (phone.text.isEmpty() || phone.text.length != 10){
                Snackbar.make(findViewById(R.id.createContactZipTxt), "Fill in Phone Number", Snackbar.LENGTH_LONG).show()
            }
            else if (email.text.isEmpty()){
                Snackbar.make(findViewById(R.id.createContactZipTxt), "Fill in Email", Snackbar.LENGTH_LONG).show()
            }
            else{
                var address : Address = Address()
                address.street = street.text.toString()
                address.city = city.text.toString()
                address.state = stateSelection
                address.zip = zip.text.toString()
                var location = address.addressToString()
                creatingUser.address = location

                
                credentialsActivity(R.layout.activity_create_user_credentials)
            }
        }

        // create action when cancel button pressed
        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }
    }

    // Intent that will open credentials activity when activated
    private fun credentialsActivity(view: Int){
        val intent = Intent(this, CreateUserCredentials::class.java)
        intent.putExtra("user", creatingUser)
        startActivity(intent)
    }

    // Intent that will open login activity when activated
    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun getAccessToCurrentUser(): Client {

        return intent.extras?.get("user") as Client

    }
}