package com.example.weightloss_pathway_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class Friday : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val view : View = inflater.inflate(R.layout.fragment_friday, container, false)

        val list = view.findViewById<ListView>(R.id.goalsList)
        val arrayList = ArrayList<String>()
        arrayList.add("150g protein")
        arrayList.add("8 Hrs of Sleep")
        arrayList.add("2 Servings of Vegetables")
        arrayList.add("2500 Calories")

        val arrayAdapter = activity?.baseContext?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1, arrayList
            )
        }
        list?.adapter = arrayAdapter
        list?.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val clickedItem = list?.getItemAtPosition(position) as String
                //Toast.makeText(this@MainActivity, clickedItem, Toast.LENGTH_LONG).show()
            }
        // Inflate the layout for this fragment
        return view
    }
}