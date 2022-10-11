package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.viewmodels.BugdetCreatorViewModel
import com.google.firebase.Timestamp
import com.example.gestionpresupuesto.databinding.FragmentBugdetCreatorBinding
import com.google.android.material.snackbar.Snackbar

class BudgetCreator : Fragment() {


    private val viewModel : BugdetCreatorViewModel by viewModels()
    private var _binding: FragmentBugdetCreatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBugdetCreatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {

        super.onStart()

        binding.siguienteButton.setOnClickListener {

            if (!binding.inputName.text.isNullOrBlank() && !binding.inputAdress.text.isNullOrBlank() && !binding.inputAdress2.text.isNullOrBlank() && !binding.inputPhone.text.isNullOrBlank() && !binding.inputAlternativePhone.text.isNullOrBlank() && !binding.inputExpirationDate.text.isNullOrBlank()){
                var action = BudgetCreatorDirections.actionBudgetCreator2ToNewBudgetFragment()
                binding.root.findNavController().navigate(action)
            }else { Snackbar.make(binding.budgetCreator, "Todos los campos deben tener valores", Snackbar.LENGTH_LONG).show()
                }
            var clientName = binding.inputName.text.toString()
            var adress =  binding.inputAdress.text.toString()
            var betweenStreets = binding.inputAdress2.text.toString()
            var phone = binding.inputPhone.text.toString()
            var alternativePhone = binding.inputAlternativePhone.text.toString()
            var dateExpirate = binding.inputExpirationDate.text.toString()

        }

       // viewModel.createBudget(budgetMock)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}