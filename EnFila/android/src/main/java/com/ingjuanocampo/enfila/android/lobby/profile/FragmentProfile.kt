package com.ingjuanocampo.enfila.android.lobby.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.lobby.profile.viewmodel.ViewModelFragmentProfile

class FragmentProfile : Fragment() {

    companion object {
        fun newInstance() = FragmentProfile()
        fun newInstance(phone: String) = FragmentProfile().apply {
            val arguments = Bundle()
            arguments.putString("phone", phone)
            this.arguments = arguments
        }

    }


    private val viewModel by viewModels<ViewModelFragmentProfile>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}