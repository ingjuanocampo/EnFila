package com.ingjuanocampo.enfila.android.assignation.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.assignation.viewmodel.ViewModelAssignation

class FragmentPhoneNumber : Fragment() {

    private val navController by lazy { NavHostFragment.findNavController(this) }

    companion object {
        fun newInstance() = FragmentPhoneNumber()
    }

    private lateinit var viewModel: ViewModelAssignation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_number, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelAssignation::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val next = view.findViewById<ImageView>(R.id.next)
        next.setOnClickListener {
            navController.navigate(R.id.action_fragmentNumber_to_fragmentNameAndNote)
        }
    }

}