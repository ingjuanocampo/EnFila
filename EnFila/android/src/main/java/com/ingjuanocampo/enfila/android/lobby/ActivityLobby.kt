package com.ingjuanocampo.enfila.android.lobby

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_SELECTED
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.history.FragmentHistory
import com.ingjuanocampo.enfila.android.lobby.home.FragmentHome
import com.ingjuanocampo.enfila.android.lobby.list.FragmentListItems
import com.ingjuanocampo.enfila.android.menu.BottomMenuBuilder

class ActivityLobby: AppCompatActivity() {
    
    private val bottomNavBuilder by lazy {
        BottomMenuBuilder()
            .appendItem(fragmentFactory = { FragmentHome.newInstance() }, icon = getDrawable(R.drawable.ic_home), title = "home")
            .appendItem(fragmentFactory = { FragmentListItems.newInstance() }, icon = getDrawable(R.drawable.ic_format_list), title = "List")
            .appendItem(fragmentFactory = { FragmentHistory.newInstance() }, icon = getDrawable(R.drawable.ic_history), title = "Hist")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.labelVisibilityMode = LABEL_VISIBILITY_SELECTED
        bottomNavBuilder.attachMenu(bottomNav, supportFragmentManager)
    }

}