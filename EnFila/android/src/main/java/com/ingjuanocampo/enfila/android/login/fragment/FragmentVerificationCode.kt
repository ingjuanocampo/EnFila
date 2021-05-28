package com.ingjuanocampo.enfila.android.login.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.login.viewmodel.ViewModelLogin

class FragmentVerificationCode : Fragment() {

    val viewModel by viewModels<ViewModelLogin> (ownerProducer = { requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.verification_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verificationCode = view.findViewById<EditText>(R.id.verificationCode)

        verificationCode.addTextChangedListener {
            viewModel.verificationCode = (it.toString())
        }


        var doVerificationButton = view.findViewById<FloatingActionButton>(R.id.floatButton)
        doVerificationButton.setOnClickListener {
            viewModel.verify()
        }


    }





}