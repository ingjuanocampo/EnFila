package com.ingjuanocampo.enfila.android.lobby.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.history.viewmodel.HomeState
import com.ingjuanocampo.enfila.android.lobby.history.viewmodel.ViewModelHome

class FragmentHome : Fragment() {

    companion object {
        fun newInstance() = FragmentHome()
    }

    private var currentNumber: TextView? = null
    private lateinit var viewModel: ViewModelHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.callAction).setOnClickListener {
            viewModel.next()
        }

        currentNumber = view.findViewById<TextView>(R.id.currentNumber)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelHome::class.java)
        viewModel.loadCurrentTurn()


        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeState.Loading -> {
                }
                HomeState.Empty -> {
                    currentNumber?.text = "---"
                }
                is HomeState.CurrentTurn -> {
                    currentNumber?.text = "${it.shift.shift.number}"
                }
            }
        })
    }

}