package com.ingjuanocampo.enfila.android.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ingjuanocampo.enfila.domain.Greeting
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.di.AppComponent

fun greet(): String {
    return Greeting().greeting()
}

class SplashActivity : AppCompatActivity() {

    private val viewModel : ViewModelSplash by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        viewModel.state.observe(this, Observer {
            finishAffinity()
            AppComponent.providesState().navigateLaunchScreen()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.launchSplash()
    }
}
