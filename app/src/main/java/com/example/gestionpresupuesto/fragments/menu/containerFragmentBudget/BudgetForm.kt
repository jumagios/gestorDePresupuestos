package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.databinding.FragmentBugdetFormBinding
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.viewmodels.MainProductListViewModel
import com.example.gestionpresupuesto.viewmodels.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp

class BudgetForm : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val mainProductListViewModel: MainProductListViewModel by viewModels()
    private var _binding: FragmentBugdetFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBugdetFormBinding.inflate(inflater, container, false)
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

        mainProductListViewModel.getAllProducts()

        mainProductListViewModel.productList.observe(viewLifecycleOwner, Observer { result ->

            sharedViewModel.setProductList(result)

            binding.siguienteButton.setOnClickListener {

                var budgetToCreate = Budget()

                if (true) {//!binding.inputName.text.isNullOrBlank() && !binding.inputAdress.text.isNullOrBlank() && !binding.inputAdress2.text.isNullOrBlank() && !binding.inputPhone.text.isNullOrBlank() && !binding.inputAlternativePhone.text.isNullOrBlank() && !binding.inputExpirationDate.text.isNullOrBlank()){

                    var parcialBudget = Budget(
                        "",
                        binding.inputName.text.toString(),
                        binding.inputAdress.text.toString(),
                        binding.inputAdress2.text.toString(),
                        binding.inputAdress3.text.toString(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        Timestamp.now(),
                        Timestamp.now().toDate().toString(),
                        false,
                        0.0,
                        mutableListOf()
                    )

                    sharedViewModel.setBudgetToCreate(parcialBudget)
                    var action = BudgetFormDirections.actionBudgetFormToBudgetCreator()
                    binding.root.findNavController().navigate(action)

                } else {
                    Snackbar.make(
                        binding.budgetCreator,
                        "Todos los campos deben tener valores",
                        Snackbar.LENGTH_LONG
                    ).show()

                }
            }
        })
    }
}
