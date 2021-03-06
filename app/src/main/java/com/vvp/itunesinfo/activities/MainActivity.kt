package com.vvp.itunesinfo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.vvp.itunesinfo.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)

        navigationController = Navigation.findNavController(this, R.id.navGraphHost)
        NavigationUI.setupActionBarWithNavController(this, navigationController)

        val appBarConfiguration = AppBarConfiguration(navigationController.graph)

        // для отображения стрелки назад в тулбаре
        toolbar.setupWithNavController(navigationController, appBarConfiguration)
    }


}