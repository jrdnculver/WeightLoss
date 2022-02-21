package com.example.weightloss_pathway_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

class WeeklyTab : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_tab)
        title = "WeightLoss Goals"
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Sun"))
        tabLayout.addTab(tabLayout.newTab().setText("Mon"))
        tabLayout.addTab(tabLayout.newTab().setText("Tue"))
        tabLayout.addTab(tabLayout.newTab().setText("Wed"))
        tabLayout.addTab(tabLayout.newTab().setText("Thu"))
        tabLayout.addTab(tabLayout.newTab().setText("Fri"))
        tabLayout.addTab(tabLayout.newTab().setText("Sat"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        /*
        val date = findViewById<FloatingActionButton>(R.id.weeklyTabDateBtn)
        val dateText = findViewById<TextView>(R.id.weeklyTabDateTxt)
        date.setOnClickListener{
            val c = Calendar.getInstance()
            val day= c.get(Calendar.DAY_OF_MONTH)
            val month =c.get(Calendar.MONTH)
            val year =c.get(Calendar.YEAR)
            val myDatePicker =
                DatePickerDialog(this,android.R.style.ThemeOverlay, DatePickerDialog.OnDateSetListener {
                        DatePicker, Year,Month,Day ->
                    dateText.text="$Day/ ${Month+1} /$Year"},year,month,day)
            myDatePicker.show()
        }
        */

        val adapter = MyTabAdapter(this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}