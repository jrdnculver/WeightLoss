package com.example.weightloss_pathway_project

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class Saturday : Fragment() {
    private lateinit var dateString : String
    private lateinit var goalList : ArrayList<String>
    private lateinit var nutritionDatabase: DatabaseReference
    private lateinit var fitnessDatabase: DatabaseReference
    private var firebaseUser : FirebaseUser? = null
    private var currentNutritionalGoals: ArrayList<NutritionalGoals>? = null
    private var currentFitnessGoals: ArrayList<FitnessGoals>? = null
    private lateinit var list : ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val view : View = inflater.inflate(R.layout.fragment_saturday, container, false)

        list = view.findViewById(R.id.goalsList)
        //dateSelection = requireView().findViewById(R.id.weeklyTabDateTxt)

        initialize()
        gettingGoals()
        createDateGoal()

        // Inflate the layout for this fragment
        return view
    }

    // Initialize all variables
    private fun initialize(){
        dateString = String()
        goalList = ArrayList()
        firebaseUser = FirebaseAuth.getInstance().currentUser
        nutritionDatabase = Firebase.database.reference.child("users").child(firebaseUser!!.uid).child("nutrition")
        fitnessDatabase = Firebase.database.reference.child("users").child(firebaseUser!!.uid).child("fitness")
        currentFitnessGoals = ArrayList()
        currentNutritionalGoals = ArrayList()

        try{
            // Set the date
            dateString = getDate()!!
        }
        catch(e : Exception){

        }
    }

    // Get date selection from activity
    private fun getDate() : String?{
        val data = arguments
        return data?.getString("date")
    }

    // Get goals from the database
    // Query occurs asynchronously and requires downhill listview setting within function
    private fun gettingGoals(){
        // Access Database
        var newNut = NutritionalGoals()
        var newFit = FitnessGoals()
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (dataSnapShot in dataSnapshot.children) {
                    newNut = dataSnapShot.getValue<NutritionalGoals>()!!
                    currentNutritionalGoals?.add(newNut)
                }
                createNutritionalGoals()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        nutritionDatabase.addValueEventListener(postListener)

        val postListener1 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (dataSnapShot in dataSnapshot.children) {
                    newFit = dataSnapShot.getValue<FitnessGoals>()!!
                    currentFitnessGoals?.add(newFit)
                }
                createFitnessGoals()

                // Will set listview values here
                setListView()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        fitnessDatabase.addValueEventListener((postListener1))
    }

    // creates listview occurrence for date
    private fun createDateGoal(){
        if (dateString != String()) {
            goalList.add(dateString)
        }
    }

    // creates nutrition goal and adds to list for listview occurrence
    fun createNutritionalGoals() {
        if (currentNutritionalGoals != null) {
            for (item: NutritionalGoals in currentNutritionalGoals!!) {
                if (item.date == dateString) {
                    goalList.add(item.nutritionalGoal)
                }
            }
        }
    }

    // creates fitness goal and adds to list for listview occurrence
    fun createFitnessGoals() {
        if (currentFitnessGoals != null) {
            for (item: FitnessGoals in currentFitnessGoals!!) {
                if (item.date == dateString) {
                    goalList.add(item.goalString())
                }
            }
        }
    }

    // sets listview values
    fun setListView(){
        val arrayAdapter = activity?.baseContext?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1, goalList
            )
        }

        list.adapter = arrayAdapter
        list.onItemClickListener =
            OnItemClickListener { _, _, position, _ ->
                val clickedItem = list.getItemAtPosition(position) as String
                //Toast.makeText(this@MainActivity, clickedItem, Toast.LENGTH_LONG).show()
            }
    }
}