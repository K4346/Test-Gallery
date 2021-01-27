package com.example.testgallery

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testgallery.presenters.GalleryPresenter
import com.example.testgallery.view.GalleryView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.actionbars.*
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class GalleryActivity : MvpAppCompatActivity(), GalleryView {
    @InjectPresenter
    lateinit var presenter: GalleryPresenter
    override fun onDestroy() {
        super.onDestroy()
        presenter.disposeComposite()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_new, R.id.navigation_popular
//            )
//        )
//
//     setupActionBarWithNavController(navController, appBarConfiguration)
//        initializeCustomActionBar()
        setActionBar(action_bar_)
//        val actionBar = supportActionBar
//        actionBar?.show()
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
        }
        else{
            arrow_button.visibility = View.GONE
            actionbar_name.visibility = View.VISIBLE

        }

    }

    fun goodConnection() {

        if (ivBadConnection.visibility == View.VISIBLE) {
            llBadConnection.visibility = View.INVISIBLE
            ivBadConnection.visibility = View.INVISIBLE
            tvTitleBadConnection.visibility = View.INVISIBLE
            tvDescriptionBadConnection.visibility = View.INVISIBLE

        }

    }

    fun badConnection() {
        if (ivBadConnection.visibility == View.INVISIBLE) {
            llBadConnection.visibility = View.VISIBLE
            ivBadConnection.visibility = View.VISIBLE
            tvTitleBadConnection.visibility = View.VISIBLE
            tvDescriptionBadConnection.visibility = View.VISIBLE
        }
    }


}