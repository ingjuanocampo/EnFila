package com.ingjuanocampo.enfila.android.assignation.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.assignation.viewmodel.ViewModelAssignation

class FragmentNumber : Fragment() {

    private val navController by lazy { NavHostFragment.findNavController(this) }

    companion object {
        fun newInstance() = FragmentNumber()
    }

    private lateinit var viewModel: ViewModelAssignation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelAssignation::class.java)
        // TODO: Use the ViewModel
    }

}