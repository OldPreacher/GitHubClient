package com.example.githubclient.ui.activity

import android.os.Bundle
import com.example.githubclient.MyApp
import com.example.githubclient.R
import com.example.githubclient.databinding.ActivityMainBinding
import com.example.githubclient.mvp.presenter.MainPresenter
import com.example.githubclient.mvp.view.MainView
import com.example.githubclient.navigation.AndroidScreens
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter {
        MainPresenter(
            MyApp.instance.router,
            AndroidScreens()
        )
    }
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        MyApp.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        MyApp.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        var handled = false
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                handled = true
                return
            }
        }
        if (!handled) {
            presenter.backClicked()
        } else {
            super.onBackPressed()
        }
    }
}