package com.example.testgallery.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testgallery.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.actionbars.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        setActionBar(action_bar_)

        navView.setupWithNavController(navController)
    }

    fun changeActionBar(toPopular: String) {
        if (toPopular == "true")
            actionbar_name.text = "Popular"
        else actionbar_name.text = "New"
    }


    fun textToButtonOnActionBar(flag: Boolean) {
        if (flag) {
            actionbar_name.visibility = View.GONE
            arrow_button.visibility = View.VISIBLE
        } else {
            arrow_button.visibility = View.GONE
            actionbar_name.visibility = View.VISIBLE

        }

    }

}