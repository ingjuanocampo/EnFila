package com.ingjuanocampo.enfila.android.menu

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ingjuanocampo.enfila.android.R

class BottomMenuBuilder {

    private val listOfItems = ArrayList<BottomMenuItem>()

    fun appendItem(fragmentFactory: () -> Fragment, title: String, icon: Drawable?): BottomMenuBuilder {
        listOfItems.add(BottomMenuItem(fragmentFactory, title, icon))
        return this
    }

    @SuppressLint("ResourceType")
    fun attachMenu(bottomNav: BottomNavigationView, activity: AppCompatActivity) {
        bottomNav.inflateMenu(R.menu.bottom_navigation_menu)
        bottomNav.menu.clear()
        listOfItems.forEach {
            val id = listOfItems.indexOf(it)
            bottomNav.menu.add(0, id, id, it.title).apply {
                icon = it.icon
            }
        }
        attachFragment(listOfItems[1].fragmentFactory(), activity.supportFragmentManager)
        activity.supportActionBar?.title = listOfItems[1].title
        bottomNav.selectedItemId = 1

        bottomNav.setOnNavigationItemSelectedListener {
            val fragment = this.listOfItems[it.itemId].fragmentFactory()
            activity.supportActionBar?.title = listOfItems[it.itemId].title

            attachFragment(fragment, activity.supportFragmentManager)
            true
        }
    }

    private fun attachFragment(fragment: Fragment, supportFragment: FragmentManager) {
        supportFragment.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }


    internal class BottomMenuItem(val fragmentFactory: () -> Fragment, val title: String, val icon: Drawable?)
}