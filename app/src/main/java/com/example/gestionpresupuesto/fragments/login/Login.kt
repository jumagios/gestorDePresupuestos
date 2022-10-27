package com.example.gestionpresupuesto.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class Login : Fragment() {

    //private lateinit var viewModel: LoginViewModel
    lateinit var v : View
    lateinit var txtUser : TextView
    lateinit var txtPass : TextView
    lateinit var txtRequestNewPassword : TextView

    lateinit var btnLogin : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        txtUser = v.findViewById(R.id.loginUser)
        txtPass = v.findViewById(R.id.loginPass)
        btnLogin = v.findViewById(R.id.btnLogin)
        txtRequestNewPassword = v.findViewById(R.id.reset_password)


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        //viewModel.createProduct("testID", 500.50) //TEST BASE DE DATOS!

        btnLogin.setOnClickListener{
            val userName = txtUser.text.toString()
            if (existUser(userName) && existPass(txtPass.text.toString())) {
                val action = LoginDirections.actionLoginToMenuActivity()
                v.findNavController().navigate(action)
            } else {
                Snackbar.make(v, "Password o usuario incorrecto", Snackbar.LENGTH_SHORT).show()
            }

        }

        txtRequestNewPassword.setOnClickListener {

            v.findNavController().navigate(LoginDirections.actionLoginToResetPasswordFragment())

        }

    }

    private fun existUser (user: String) : Boolean {

        return true
    }

    private fun existPass (pass: String) : Boolean {

        return true
    }
}