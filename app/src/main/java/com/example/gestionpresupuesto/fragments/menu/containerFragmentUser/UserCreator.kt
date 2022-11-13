package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gestionpresupuesto.databinding.FragmentUserCreatorBinding
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.repository.UserRepository
import com.example.gestionpresupuesto.viewmodels.UserCreatorViewModel
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetCreatorDirections
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetFormDirections
import com.example.gestionpresupuesto.viewmodels.BudgetCreatorViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp

class UserCreator : Fragment() {
    private lateinit var binding: FragmentUserCreatorBinding

    companion object {
        fun newInstance() = UserCreator()
    }

    private val viewModel: UserCreatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserCreatorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.success.observe(viewLifecycleOwner) {
            if (it.toString() == "success") {
                Snackbar.make(binding.frameLayout6, "Usaurio creado con exito", Snackbar.LENGTH_SHORT)
                    .show()
                Handler(Looper.getMainLooper()).postDelayed({
                    var action = UserCreatorDirections.actionUserCreatorToLogin()
                    binding.root.findNavController().navigate(action)
                }, 450)

            } else Snackbar.make(binding.frameLayout6, it, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onStart() {
        super.onStart()


        binding.userAcceptButton.setOnClickListener {
            if (!binding.Useremail.text.toString()
                    .isNullOrBlank() && !binding.userdni.text.toString().isNullOrBlank() &&
                !binding.userName.text.toString().isNullOrBlank() && !binding.userPassword.text.toString().isNullOrBlank()
            ) {

                viewModel.registerUser(
                    User(
                        binding.userSwitch.isChecked, binding.userdni.text.toString(), binding.Useremail.text.toString(),
                        binding.userName.text.toString(), binding.userPassword.text.toString(), false  //isChecked : si esta chequeado graba deschequeado
                    )

                )

            }
                else {
                    Snackbar.make(
                        binding.frameLayout6,
                        "Todos los campos deben tener datos",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }




        binding.userCancelButton.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("¿Desea cancelar la creacion del Usuario?")
                .setCancelable(false)
                .setPositiveButton("Aceptar", DialogInterface.OnClickListener {
                        dialog, id ->

                    var action = UserCreatorDirections.actionUserCreatorToUserList()
                    binding.root.findNavController().navigate(action)


                })
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("")
            alert.show()
        }
    }
}