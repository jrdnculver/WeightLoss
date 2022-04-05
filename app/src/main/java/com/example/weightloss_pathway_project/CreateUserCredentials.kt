package com.example.weightloss_pathway_project

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class CreateUserCredentials : AppCompatActivity() {
    private lateinit var creatingUser : Client
    private lateinit var  auth: FirebaseAuth
    private lateinit var fireData: FirebaseFirestore
    private lateinit var database: DatabaseReference
    private lateinit var finish: Button
    private lateinit var cancel: Button
    private lateinit var email: TextView
    private lateinit var password: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        // Page Title
        title = "Account Creation"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user_credentials)

        // Access previous user account
        creatingUser = getAccessToCurrentUser()

        // Initialize Firebase Instance for Authentication
        auth= FirebaseAuth.getInstance()
        database = Firebase.database.reference
        fireData = FirebaseFirestore.getInstance()

        instantiate()
        onClick()


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

    // Instantiate object from XML
    private fun instantiate(){
        // link finish button
        finish = findViewById(R.id.createUserFinishBtn)
        // link cancel button
        cancel = findViewById(R.id.createUserCancelBtn)

        // Get value for username
        email = findViewById(R.id.createEmailTxt)
        // Get value for password
        password = findViewById(R.id.createPasswordTxt)
    }

    // Will provide functionality with onClick commands
    private fun onClick(){
        // create action when finish button pressed
        finish.setOnClickListener{
            // Turn values to string for creating user function in firebase
            val emails = email.text.toString()
            val passwords = password.text.toString()

            if (email.text.isEmpty()){
                Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show()
            }
            else if (password.text.isEmpty()){
                Toast.makeText(this, "Enter Valid Password", Toast.LENGTH_SHORT).show()
            }
            else {
                val mail = Email()
                mail.isEmail = mail.isValidEmail(emails)

                if (mail.isEmail){
                    creatingUser.email = emails.trim()
                    creatingUser.password = passwords.trim()

                    // If text are not null, create new account
                    auth.createUserWithEmailAndPassword(emails, passwords)
                        .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            // Save User
                            writeNewUser(creatingUser.firstname, creatingUser.lastname, creatingUser.address, creatingUser.email, creatingUser.phone,
                                        creatingUser.birthday, creatingUser.isAdmin)
                            // Return to login page after account created
                            finishedCreatedActivity(R.layout.activity_login)
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
                                    this@CreateUserCredentials, e.localizedMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show()
                }
            }
        }
        // create action when cancel button pressed
        cancel.setOnClickListener{
            loginActivity(R.layout.activity_login)
        }
    }

    // Class constructor for data input for new Account creation
    @IgnoreExtraProperties
    data class SaveUser(val firstname: String? = null, val lastname: String? = null, val address : String? = null,
        val email : String? = null, val phone : String? = null, val birthday : String?, val isAdmin : Boolean = false ) {
        // Null default values create a no-argument default constructor, which is needed
        // for deserialization from a DataSnapshot.
    }

    // Write new Account to database with username as userId
    fun writeNewUser(firstname: String, lastname: String, address: String, email : String, phone: String, birthday : String, isAdmin: Boolean) {
        val user = SaveUser(firstname, lastname, address, email, phone, birthday, isAdmin)

        database.child("users").child(FirebaseAuth.getInstance().getCurrentUser()!!.getUid()).child("account").setValue(user)
    }
}