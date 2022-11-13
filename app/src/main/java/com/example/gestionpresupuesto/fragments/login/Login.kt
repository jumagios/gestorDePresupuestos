package com.example.gestionpresupuesto.fragments.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.activities.MenuActivity
import com.example.gestionpresupuesto.databinding.FragmentLoginBinding
import com.example.gestionpresupuesto.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()
    private var OK = false

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

            val imm =  context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view?.windowToken , 0)


            val email = binding.loginUserMail.text.toString()
            var password = binding.loginUserPassword.text.toString()

            email.length.toString()
            password.length.toString()

            if (email.isNullOrBlank() || password.isNullOrBlank()) {

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

                    if (userFound.size == 0) {

                        Snackbar.make(
                            binding.frameLayout,
                            "El usuario no existe",
                            Snackbar.LENGTH_LONG
                        )
                            .show()

                    } else {

                        if (userFound.size == 1) {

                            if (userFound.get(0).erased) {

                                Snackbar.make(
                                    binding.frameLayout,
                                    "Usuario inhabilitado",
                                    Snackbar.LENGTH_LONG
                                )
                                    .show()

                            } else {

                                viewModel.OK.value = true


                            }

                        }


                    }

                })

            }

            viewModel.OK.observe(viewLifecycleOwner, Observer { OK ->

                login()

            })


        }

        binding.resetPassword.setOnClickListener() {

            binding.root.findNavController()
                .navigate(LoginDirections.actionLoginToResetPasswordFragment())

        }
    }

    private fun login() {

        val email = binding.loginUserMail.text.toString()
        var password = binding.loginUserPassword.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    Snackbar.make(
                        binding.frameLayout,
                        "Login correcto",
                        Snackbar.LENGTH_SHORT
                    )
                        .show()

                    Handler(Looper.getMainLooper()).postDelayed({

                        val i = Intent(
                            this@Login.requireContext(),
                            MenuActivity::class.java
                        )
                        startActivity(i)

                    }, 1200)


                } else {
                    Snackbar.make(
                        binding.frameLayout,
                        "Email o Contraseña incorrecta",
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }
            }
    }
}