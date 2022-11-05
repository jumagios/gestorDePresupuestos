package com.example.gestionpresupuesto.fragments.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.activities.MenuActivity
import com.example.gestionpresupuesto.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onStart() {
        super.onStart()


        /*
        binding.btnLogin.setOnClickListener {

           // binding.root.findNavController().navigate(
              //  LoginDirections.actionLoginToMenuActivity())

            val i = Intent(this@Login.requireContext(), MenuActivity::class.java)
            startActivity(i)

        }
        */


        binding.btnLogin.setOnClickListener {
            val email = binding.loginUserMail.text.toString()
            var password = binding.loginUserPassword.text.toString()

            if (!email.isNullOrBlank() && !password.isNullOrBlank()) {
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

                                /*
                                binding.root.findNavController().navigate(
                                    LoginDirections.actionLoginToMenuActivity())
                                    */

                                val i = Intent(this@Login.requireContext(), MenuActivity::class.java)
                                startActivity(i)

                            }, 200)


                        } else {
                            Snackbar.make(
                                binding.frameLayout,
                                "Email o Contraseña incorrecta",
                                Snackbar.LENGTH_LONG
                            )
                                .show()
                        }
                    }
            } else {
                Snackbar.make(
                    binding.frameLayout,
                    "Datos invalidos",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }


        binding.resetPassword.setOnClickListener() {

            binding.root.findNavController()
                .navigate(LoginDirections.actionLoginToResetPasswordFragment())

        }
    }
}