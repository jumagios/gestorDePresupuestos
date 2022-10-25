package com.example.gestionpresupuesto.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.databinding.FragmentProductCreatorBinding
import com.example.gestionpresupuesto.databinding.FragmentResetPasswordBinding
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetCreatorDirections
import com.example.gestionpresupuesto.viewmodels.ResetPasswordViewModel
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ResetPasswordFragment()
    }

    private lateinit var viewModel: ResetPasswordViewModel
    private lateinit var binding: FragmentResetPasswordBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onStart() {
        super.onStart()

        binding.buttonResetPassword.setOnClickListener{

                sendMail()
            }
        }

    private fun sendMail() {
        val email = binding.inputResetPasswordEmail.text.toString()
        if (email.isNullOrBlank()) {
            com.google.android.material.snackbar.Snackbar.make(binding.root, "Por favor ingresar su Email", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT)
                .show()
        } else {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        com.google.android.material.snackbar.Snackbar.make(
                            binding.root,
                            "Se envio el mail con exito",
                            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                        ).show()

                        Handler(Looper.getMainLooper()).postDelayed({

                            binding.root.findNavController().navigate(ResetPasswordFragmentDirections.actionResetPasswordFragmentToLogin())

                        }, 1500)

                    } else {
                        com.google.android.material.snackbar.Snackbar.make(
                            binding.root,
                            "Problemas para enviar el mail de recuperación",
                            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}