package com.example.weightloss_pathway_project

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Login : AppCompatActivity() {
    private lateinit var database: DatabaseReference
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

        // Access Database
        database = Firebase.database.reference

        // Access Authentication
        auth = FirebaseAuth.getInstance()

        // OnClick functionality to login
        onClick()


    }

    // Intent that will open main activity when activated
    private fun mainActivity(view: Int){
        val intent = Intent(this, Main::class.java)
        startActivity(intent)
    }

    // Intent that will open create user activity when activated
    private fun createNameActivity(view: Int){
        val intent = Intent(this, CreateUserName::class.java).apply {
            startActivity(this)
        }
    }

    // Custom details for logging in to firebase
    private fun loginUser(view : Int) {
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
                        mainActivity(R.layout.activity_main)
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
    fun onClick(){
        // link sign in button
        signIn = findViewById<Button>(R.id.loginSignInBtn)
        // link create account button
        createAccount = findViewById<Button>(R.id.loginCreateAccountBtn)

        // create action when finish button pressed
        signIn.setOnClickListener{
            loginUser(R.layout.activity_login)
        }

        // create action when createAccount button pressed
        createAccount.setOnClickListener{
            createNameActivity(R.layout.activity_create_user_name)
        }
    }
}