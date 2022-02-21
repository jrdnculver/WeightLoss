package com.example.weightloss_pathway_project

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable
import java.util.*

class CreateUserName : AppCompatActivity() {
    private var creatingUser : Client = Client()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_name)
        title = "Account Creation"

        // Gets value of user first name
        val firstname = findViewById<TextView>(R.id.createUserFirstNameTxt)
        // Gets value of user last name
        val lastname = findViewById<TextView>(R.id.createUserLastNameTxt)
        // Gets value of user birthday
        val dateText = findViewById<TextView>(R.id.createUserBirthTxt)
        // Access's birthday button
        val dateBtn = findViewById<Button>(R.id.createUserBirthBtn)

        // link next button
        val next = findViewById<Button>(R.id.createUserNameNextBtn)
        // link cancel button
        val cancel = findViewById<Button>(R.id.createUserCancelBtn)

        // create action when next button pressed
        next.setOnClickListener{
            if (firstname.text.isEmpty()){
                Snackbar.make(findViewById(R.id.createUserFirstNameTxt), "Fill in First Name", Snackbar.LENGTH_LONG).show()
            }
            else if (lastname.text.isEmpty()){
                Snackbar.make(findViewById(R.id.createUserFirstNameTxt), "Fill in Last Name", Snackbar.LENGTH_LONG).show()
            }
            else if (dateText.text.isEmpty()){
                Snackbar.make(findViewById(R.id.createUserFirstNameTxt), "Fill in Birthdate", Snackbar.LENGTH_LONG).show()
            }
            else {

                var first = firstname.text.toString()
                // Valid and assign first name
                var validFirstName = creatingUser.checkName(firstname.text.toString())
                creatingUser.firstName = validFirstName

                // Valid and assign last name
                var validLastName = creatingUser.checkName(lastname.text.toString())
                creatingUser.lastName = validLastName

                // Valid and assign date
                var validMonth = creatingUser.checkMonth(dateText.text.toString())
                var validDay = creatingUser.checkDay(dateText.text.toString())
                var validYear = creatingUser.checkYear(dateText.text.toString())

                // Assign valid date values to user account
                var birthday: BirthDate = BirthDate()
                birthday.month = validMonth.trim()
                birthday.day = validDay.trim()
                birthday.year = validYear.trim()
                creatingUser.birthday = birthday.birthdayToString(birthday)


                contactInfoActivity(R.layout.activity_create_user_contact_info)
            }

        }

        // create action when cancel button pressed
        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }

        // Access Calendar to select and format birthdate
        dateBtn.setOnClickListener{
            val c = Calendar.getInstance()
            val day= c.get(Calendar.DAY_OF_MONTH)
            val month =c.get(Calendar.MONTH)
            val year =c.get(Calendar.YEAR)
            val myDatePicker =
                DatePickerDialog(this,android.R.style.ThemeOverlay, DatePickerDialog.OnDateSetListener {
                        _, Year,Month,Day ->
                    dateText.text=String.format("${Month+1} / $Day / $Year")},year,month,day)
            myDatePicker.show()
        }
    }

    // Intent that will open contact info activity when activated
    private fun contactInfoActivity(view: Int){
        val intent = Intent(this, CreateUserContactInfo::class.java)
        intent.putExtra("user", creatingUser)
        startActivity(intent)
    }

    // Intent that will open login activity when activated
    private fun loginActivity(view: Int){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}