package com.ingjuanocampo.enfila.android.lobby.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.ingjuanocampo.cdapter.CompositeDelegateAdapter
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.list.adapter.DelegateShift
import com.ingjuanocampo.enfila.android.lobby.list.viewmodel.ViewModelListItems
import com.ingjuanocampo.enfila.android.utils.ViewTypes

class FragmentListItems : Fragment() {

    private lateinit var adapter: CompositeDelegateAdapter
    val viewModel: ViewModelListItems by viewModels()

    companion object {
        fun newInstance() = FragmentListItems()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.load()
        viewModel.state.observe(this, Observer {
            adapter.addNewItems(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_items, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.recycler)

        adapter = CompositeDelegateAdapter(1).apply {
            appendDelegate(
                ViewTypes.SHIFT.ordinal
            ) { DelegateShift(it) }
        }
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), OrientationHelper.VERTICAL))
        recycler.adapter = adapter

    }
}