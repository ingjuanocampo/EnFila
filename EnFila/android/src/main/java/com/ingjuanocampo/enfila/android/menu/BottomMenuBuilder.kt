package com.ingjuanocampo.enfila.android.menu

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ingjuanocampo.enfila.android.R

class BottomMenuBuilder {

    private val listOfItems = ArrayList<BottomMenuItem>()

    fun appendItem(fragment: Fragment, title: String, icon: Drawable?): BottomMenuBuilder {
        listOfItems.add(BottomMenuItem(fragment, title, icon))
        return this
    }

    fun attachMenu(bottomNav: BottomNavigationView, supportFragment: FragmentManager) {
        bottomNav.inflateMenu(R.menu.bottom_navigation_menu)
        bottomNav.menu.clear()
        listOfItems.forEach {
            val id = listOfItems.indexOf(it)
            bottomNav.menu.add(0, id, id, it.title).apply {
                icon = it.icon
            }
        }
        attachFragment(listOfItems[0].fragment, supportFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            val fragment = this.listOfItems[it.itemId].fragment
            attachFragment(fragment, supportFragment)
            true
        }
    }

    private fun attachFragment(fragment: Fragment, supportFragment: FragmentManager) {
        supportFragment.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }


    internal class BottomMenuItem(val fragment: Fragment, val title: String, val icon: Drawable?)
}