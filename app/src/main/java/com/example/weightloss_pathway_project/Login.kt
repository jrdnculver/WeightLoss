package com.example.weightloss_pathway_project

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ValueEventListener
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class Login : AppCompatActivity() {
    private var currentUser : Client? = null
    private var firebaseUser : FirebaseUser? = null
    private lateinit var database: DatabaseReference
    private lateinit var databaseValues : ArrayList<Client?>
    private lateinit var auth: FirebaseAuth
    private lateinit var signIn: Button
    private lateinit var createAccount: Button
    private lateinit var email: TextView
    private lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Set title of page to Login
        title = "Login"

        firebaseUser = FirebaseAuth.getInstance().currentUser

        // Access Authentication
        auth = FirebaseAuth.getInstance()

        // OnClick functionality to login
        onClick()

        loggedIn()
    }

    // Custom details for logging in to firebase
    private fun loggingInUser(view : Int) {
        // Get email input of user
        email = findViewById(R.id.loginUserTxt)
        // Get password input of user
        password = findViewById(R.id.loginPasswordTxt)
        // Change input to strings for firebase function collaboration

        val emails = email.text.toString()
        val passwords = password.text.toString()

        // Input validation
        if (!TextUtils.isEmpty(emails) && !TextUtils.isEmpty(passwords)) {
            auth.signInWithEmailAndPassword(emails, passwords)
                .addOnCompleteListener(this@Login) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with signed-in user's information
                        loggedInToMain(R.layout.activity_main)
                    } else {
                        // If sign in fails, display a message to the user.
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthInvalidUserException) {
                            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                        } catch (e: FirebaseNetworkException) {
                            Toast.makeText(
                                this,
                                "Connect to Network",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch(e: Exception){
                            Toast.makeText(
                                this@Login, e.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }


    }

    // Will handle onClick functionality
    private fun onClick(){
        // link sign in button
        signIn = findViewById<Button>(R.id.loginSignInBtn)
        // link create account button
        createAccount = findViewById<Button>(R.id.loginCreateAccountBtn)

        // create action when finish button pressed
        signIn.setOnClickListener{
            loggingInUser(R.layout.activity_login)
        }

        // create action when createAccount button pressed
        createAccount.setOnClickListener{
            createNameActivity(R.layout.activity_create_user_name)
        }
    }

    private fun loggedIn(){
        // Access Database
        database = Firebase.database.reference.child("users").child(firebaseUser!!.uid)
        databaseValues = ArrayList()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                var obj : Client? = dataSnapshot.getValue<Client?>()
                databaseValues.add(obj)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(postListener)
    }

    // Intent that will open create user activity when activated
    private fun createNameActivity(view: Int){
        val intent = Intent(this, CreateUserName::class.java).apply {
            startActivity(this)
        }
    }

    private fun loggedInToMain(view : Int) {
        val intent = Intent(this, Main::class.java)
        for (users in databaseValues){
            if (users?.email == email.text.toString()){
                currentUser = users
            }
        }
        intent.putExtra("currentUser", currentUser)
        startActivity(intent)
    }
}