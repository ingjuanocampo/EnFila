package com.ingjuanocampo.enfila.android.login.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ingjuanocampo.enfila.android.R
import com.ingjuanocampo.enfila.android.login.viewmodel.LoginState
import com.ingjuanocampo.enfila.android.login.viewmodel.ViewModelLogin

class FragmentLoginPhoneNumber: Fragment() {

    private lateinit var doVerificationButton: FloatingActionButton
    private val navController by lazy { NavHostFragment.findNavController(this) }

    val viewModel by viewModels<ViewModelLogin> (ownerProducer = { requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_phone_number, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phoneNumber = view.findViewById<EditText>(R.id.phoneNumber)

        phoneNumber.addTextChangedListener {
            viewModel.phoneNumber = (it.toString())
        }

        doVerificationButton = view.findViewById(R.id.floatButton)
        doVerificationButton.isEnabled = false
        doVerificationButton.setOnClickListener {
            viewModel.doLogin(requireActivity())
        }

        viewModel.state.observe(viewLifecycleOwner, {
            when(it) {
                LoginState.ToVerifyCode -> navController.navigate(R.id.action_fragmentLoginPhoneNumber_to_fragmentVerificationCode)
                LoginState.Authenticated -> Log.d("Login", "Authenticated")
                is LoginState.AuthError -> Log.e("Login", it.e.toString())
                LoginState.NumberSet -> doVerificationButton.isEnabled = true
            }
        })

    }


}