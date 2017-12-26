package com.example.hackerhgl.buttons

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var mBottomNavigationView: BottomNavigationView
    private var mCurrentFragment = R.id.item_home


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fargment = supportFragmentManager
        val transaction = fargment.beginTransaction()
        transaction.replace(R.id.fragment_content, HomeFragment()).commit()

        initBottomNavigationView();
    }

    private fun initBottomNavigationView() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation)
        mBottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId
            val check = id != mCurrentFragment
            val fargment = supportFragmentManager
            val transaction = fargment.beginTransaction()

            when {
                id == R.id.item_home && check -> {
                    mCurrentFragment = R.id.item_home
                    transaction.replace(R.id.fragment_content, HomeFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                id == R.id.item_image && check -> {
                    mCurrentFragment = R.id.item_image
                    transaction.replace(R.id.fragment_content, ImageFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                id == R.id.item_tab && check -> {
                    mCurrentFragment = R.id.item_tab
                    transaction.replace(R.id.fragment_content, TabFragment()).commit()
                    return@setOnNavigationItemSelectedListener true

                }
                else -> false
            }
            true
        }

    }


}
