package com.example.gestionpresupuesto.fragments.menu.containerFragmentUser

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.databinding.FragmentUserCreatorBinding
import com.example.gestionpresupuesto.repository.UserRepository
import com.example.gestionpresupuesto.viewmodels.UserCreatorViewModel
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.viewmodels.BudgetCreatorViewModel
import com.google.android.material.snackbar.Snackbar

class UserCreator : Fragment() {
    private lateinit var binding: FragmentUserCreatorBinding
    private lateinit var repository: UserRepository
    private lateinit var v : View

    companion object {
        fun newInstance() = UserCreator()
    }

//    private lateinit var viewModel: UserCreatorViewModel
    private val viewModel: UserCreatorViewModel by viewModels()
    private lateinit var userCreator: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserCreatorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.userAcceptButton.setOnClickListener {
            var user = User
            if (!binding.Useremail.text.toString()
                    .isNullOrBlank() && !binding.userdni.text.toString().isNullOrBlank() &&
                !binding.userName.text.toString().isNullOrBlank() && !binding.userPassword.text.toString().isNullOrBlank()
            ) {

                viewModel.registerUser(
                    User(
                        binding.userSwitch.isChecked, binding.userdni.text.toString(), binding.Useremail.text.toString(),
                        binding.userName.text.toString(), binding.userPassword.text.toString()  //isChecked : si esta chequeado graba deschequeado
                    )

                )
                Snackbar.make(
                    binding.frameLayout6,
                    "Usuario grabado con Exito!",
                    Snackbar.LENGTH_LONG
                ).show()

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