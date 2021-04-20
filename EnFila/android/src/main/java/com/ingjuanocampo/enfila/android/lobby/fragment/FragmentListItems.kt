package com.ingjuanocampo.enfila.android.lobby.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.fragment.viewmodel.ViewModelListItems

class FragmentListItems : Fragment() {

    companion object {
        fun newInstance() = FragmentListItems()
    }

    private lateinit var viewModel: ViewModelListItems

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_items, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelListItems::class.java)
        // TODO: Use the ViewModel
    }

}