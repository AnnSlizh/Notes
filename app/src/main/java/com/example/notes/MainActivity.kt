package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.example.notes.fragments.HistoryFragment
import com.example.notes.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar
    private lateinit var historyButton: ImageButton
    private lateinit var homeButton: ImageButton

    private lateinit var navController: NavController
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        toolBar = findViewById(R.id.toolBar)
//        setSupportActionBar(toolBar)


        historyButton = findViewById(R.id.historyButton)

        historyButton.setOnClickListener {
            replaceFragment(HistoryFragment())
        }

        homeButton = findViewById(R.id.homeButton)

        homeButton.setOnClickListener {
            replaceFragment(HomeFragment())
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }

}