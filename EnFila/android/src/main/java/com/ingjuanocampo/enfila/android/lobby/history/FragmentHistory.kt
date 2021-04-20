package com.ingjuanocampo.enfila.android.lobby.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.history.viewmodel.ViewModelHistory

class FragmentHistory : Fragment() {

    companion object {
        fun newInstance() = FragmentHistory()
    }

    private lateinit var viewModel: ViewModelHistory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelHistory::class.java)
        // TODO: Use the ViewModel
    }

}