package com.ingjuanocampo.enfila.android.lobby.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.home.viewmodel.HomeState
import com.ingjuanocampo.enfila.android.lobby.home.viewmodel.ViewModelHome
import com.ingjuanocampo.enfila.domain.usecases.model.ShiftWithClient

class FragmentHome : Fragment() {

    companion object {
        fun newInstance() = FragmentHome()
    }

    private var toolbar: Toolbar? = null
    private var currentNumber: TextView? = null
    private var clientName: TextView? = null
    private var clientPhone: TextView? = null
    private var waitTime: TextView? = null
    private var totalInline: TextView? = null
    private var totalAverageTime: TextView? = null

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

        toolbar = view.findViewById<Toolbar>(R.id.toolbarWidget)
       setHasOptionsMenu(true)
        currentNumber = view.findViewById<TextView>(R.id.currentNumber)
        clientName = view.findViewById<TextView>(R.id.clientName)
        clientPhone = view.findViewById<TextView>(R.id.clientPhone)
        waitTime = view.findViewById<TextView>(R.id.waitTime)
        totalInline = view.findViewById<TextView>(R.id.totalInline)
        totalAverageTime = view.findViewById<TextView>(R.id.totalAverageTime)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard_menu, menu)
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
                    setEmpty()
                }
                is HomeState.CurrentTurn -> {
                    updateShift(it.shift)
                }
                is HomeState.HomeLoaded -> {
                    (requireActivity() as AppCompatActivity).supportActionBar?.title = it.home.selectedCompany.name
                    totalInline?.text = it.home.totalTurns.toString()
                    totalAverageTime?.text = it.home.avrTime.toString()
                    updateShift(it.home.currentTurn)

                }
            }
        })
    }

    private fun setEmpty() {
        currentNumber?.text = "---"
        clientName?.text = "---"
        clientPhone?.text = "---"
        waitTime?.text = "---"
    }

    private fun updateShift(shift: ShiftWithClient?) {
        shift?.let {
            clientName?.text = shift.client.name
            clientPhone?.text = shift.client.phone
            waitTime?.text = "${shift.shift.date}"
            currentNumber?.text = "${shift.shift.number}"
        } ?: setEmpty()
    }


}