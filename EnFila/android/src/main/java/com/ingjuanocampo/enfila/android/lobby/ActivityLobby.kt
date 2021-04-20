package com.ingjuanocampo.enfila.android.lobby

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.history.FragmentHistory
import com.ingjuanocampo.enfila.android.lobby.home.FragmentHome
import com.ingjuanocampo.enfila.android.lobby.list.FragmentListItems
import com.ingjuanocampo.enfila.android.menu.BottomMenuBuilder

class ActivityLobby: AppCompatActivity() {
    
    private val bottomNavBuilder by lazy {
        BottomMenuBuilder()
            .appendItem(fragment = FragmentHome.newInstance(), icon = getDrawable(R.drawable.ic_home), title = "home")
            .appendItem(fragment = FragmentListItems.newInstance(), icon = getDrawable(R.drawable.ic_format_list), title = "List")
            .appendItem(fragment = FragmentHistory.newInstance(), icon = getDrawable(R.drawable.ic_history), title = "Hist")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavBuilder.attachMenu(bottomNav, supportFragmentManager)
    }

}