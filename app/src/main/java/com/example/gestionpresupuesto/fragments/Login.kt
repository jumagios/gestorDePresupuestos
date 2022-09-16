package com.example.gestionpresupuesto.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.User
import com.google.android.material.snackbar.Snackbar

class Login : Fragment() {

    lateinit var v : View
    lateinit var txtUser : TextView
    lateinit var txtPass : TextView
    lateinit var btnLogin : Button
    var userList : MutableList<User> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        txtUser = v.findViewById(R.id.loginUser)
        txtPass = v.findViewById(R.id.loginPass)
        btnLogin = v.findViewById(R.id.btnLogin)

        userList.add(User("lucas","lopez"))

        return v
    }

    override fun onStart() {
        super.onStart()

        btnLogin.setOnClickListener{
            val userName = txtUser.text.toString()
            if (existUser(userName) && existPass(txtPass.text.toString())) {
                val action = LoginDirections.actionLoginToMain()
                v.findNavController().navigate(action)
            } else {
                Snackbar.make(v, "Password o usuario incorrecto", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    private fun existUser (user: String) : Boolean {
        var exist = false

        userList.forEach {
            if (it.user == user) {
                exist = true
            }
        }

        return exist
    }

    private fun existPass (pass: String) : Boolean {
        var exist = false

        userList.forEach {
            if (it.password == pass) {
                exist = true
            }
        }
        return exist
    }




}