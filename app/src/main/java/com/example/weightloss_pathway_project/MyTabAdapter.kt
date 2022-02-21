package com.example.weightloss_pathway_project

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
@Suppress("DEPRECATION")
// Create adapter for Tab Layout for DayOfWeek
internal class MyTabAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    // Calls the fragment when the tab is pressed
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                DayOfWeek()
            }
            1 -> {
                DayOfWeek()
            }
            2 -> {
                DayOfWeek()
            }
            3 -> {
                DayOfWeek()
            }
            4 -> {
                DayOfWeek()
            }
            5 -> {
                DayOfWeek()
            }
            6 -> {
                DayOfWeek()
            }
            7 -> {
                DayOfWeek()
            }
            else -> getItem(position)
        }
    }

    // Describes the number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}