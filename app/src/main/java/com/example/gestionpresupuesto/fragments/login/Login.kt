package com.example.gestionpresupuesto.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.databinding.FragmentLoginBinding
import com.example.gestionpresupuesto.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onStart() {
        super.onStart()

        binding.btnLogin.setOnClickListener {

            val email = binding.loginUserMail.text.toString()
            var password = binding.loginUserPassword.text.toString()

            email.length.toString()
            password.length.toString()


            if (email.isNullOrBlank() && password.isNullOrBlank()) {

                Snackbar.make(
                    binding.frameLayout,
                    "Datos incompletos",
                    Snackbar.LENGTH_LONG
                )
                    .show()

            } else {

                viewModel.getUserState(binding.loginUserMail.text.toString())

                viewModel.userList.observe(viewLifecycleOwner, Observer { userList ->

                var userFound = userList


                //no existe
                Snackbar.make(
                    binding.frameLayout,
                    "El usuario no existe",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            })

                binding.resetPassword.setOnClickListener() {

                    binding.root.findNavController()
                        .navigate(LoginDirections.actionLoginToResetPasswordFragment())

                }
            }
        }
    }
}
