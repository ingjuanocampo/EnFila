package com.ingjuanocampo.enfila.android.assignation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.ingjuanocampo.enfila.android.R


class FragmentNameNote : Fragment() {

    private val navController by lazy { NavHostFragment.findNavController(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_note, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentNameNote()
    }
}