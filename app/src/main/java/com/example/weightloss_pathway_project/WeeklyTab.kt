package com.example.weightloss_pathway_project

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.time.LocalDate
import java.util.*


class WeeklyTab : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var dateSelection: TextView
    private var firebaseUser : FirebaseUser? = null
    private lateinit var submit : Button
    private lateinit var date: Button
    private val TAG = "MyValue"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_tab)
        title = "WeightLoss Goals"

        initialize()
        onClick()
        initializeFragment()
        if(dateSelection.text.toString() == ""){
            dateSelection.text = getCurrentDay().toString()
            Handler().postDelayed({
                setTabs()
            }, 1000)
        }
    }

    private fun initialize() {
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

        dateSelection = findViewById(R.id.weeklyTabDateTxt)
        date = findViewById(R.id.weeklyTabDateBtn)
        submit = findViewById(R.id.weeklyTabDateSubmitBtn)
        firebaseUser = FirebaseAuth.getInstance().currentUser
    }

    private fun initializeFragment() {
        Log.d(TAG, "${tabLayout.tabCount}")
        val adapter = MyTabAdapter(
            this, supportFragmentManager,
            tabLayout.tabCount
        )
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 7
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    private fun onClick() {
        val DOW = DayOfWeek()
        val DATE = Date()
        date.setOnClickListener {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val myDatePicker =
                DatePickerDialog(
                    this,
                    android.R.style.ThemeOverlay,
                    { _, Year, Month, Day  ->
                        DOW.dd = Day
                        DOW.mm = Month + 1
                        DOW.yyyy = Year
                        dateSelection.text = "${DATE.monthToString(Month + 1)} $Day, $Year, ${DOW.calculate()}"
                    },
                    year,
                    month,
                    day
                )
            myDatePicker.show()
        }

        submit.setOnClickListener{
            setTabs()
        }

    }

    fun selectPage(pageIndex: Int) {
        tabLayout.setScrollPosition(pageIndex, 0f, true)
        viewPager.currentItem = pageIndex
    }

    fun getDay() : String{
        var splitDate = dateSelection.text.toString().replace(",", "").split(" ")
        return splitDate[3]
    }

    fun getCurrentDay() : String{
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK]
        Log.e("Day", "$day")

        val date = LocalDate.now()
        val d = Date()
        val dateSplit = date.toString().split(("-"))
        val month = d.monthToString(dateSplit[1].toInt())
        val dae = dateSplit[2].toInt().toString()
        val year = dateSplit[0].toInt().toString()
        val weekDay = d.dayToString(day - 1)

        return "$month $dae, $year, $weekDay"



    }

    fun setTabs(){
        val da = DayOfWeek()
        val sunday = da.findDayOfWeek(dateSelection.text.toString())
        Log.e("Value", sunday)

        val sun = Sunday()
        val bunSun = Bundle()
        bunSun.putString("date", sunday)
        sun.arguments = bunSun
        supportFragmentManager.beginTransaction().replace(R.id.fragSunday, sun).commit()

        val mon = Monday()
        val bunMon = Bundle()
        val monday = da.nextDay(sunday)
        bunMon.putString("date", monday)
        mon.arguments = bunMon
        supportFragmentManager.beginTransaction().replace(R.id.fragMonday, mon).commit()

        val tue = Tuesday()
        val bunTue = Bundle()
        val tuesday = da.nextDay(monday)
        bunTue.putString("date", tuesday)
        tue.arguments = bunTue
        supportFragmentManager.beginTransaction().replace(R.id.fragTuesday, tue).commit()

        val wed = Wednesday()
        val bunWed = Bundle()
        val wednesday = da.nextDay(tuesday)
        bunWed.putString("date", wednesday)
        wed.arguments = bunWed
        supportFragmentManager.beginTransaction().replace(R.id.fragWednesday, wed).commit()

        val thu = Thursday()
        val bunThu = Bundle()
        val thursday = da.nextDay(wednesday)
        bunThu.putString("date", thursday)
        thu.arguments = bunThu
        supportFragmentManager.beginTransaction().replace(R.id.fragThursday, thu).commit()

        val fri = Friday()
        val bunFri = Bundle()
        val friday = da.nextDay(thursday)
        bunFri.putString("date", friday)
        fri.arguments = bunFri
        supportFragmentManager.beginTransaction().replace(R.id.fragFriday, fri).commit()

        val sat = Saturday()
        val bunSat = Bundle()
        val saturday = da.nextDay(friday)
        bunSat.putString("date", saturday)
        sat.arguments = bunSat
        supportFragmentManager.beginTransaction().replace(R.id.fragSaturday, sat).commit()

        val d = Date()
        val index = d.dayToNumber(getDay())
        selectPage(index)
    }
}
