package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.viewmodels.BugdetCreatorViewModel
import com.example.gestionpresupuesto.databinding.FragmentBugdetCreatorBinding
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.viewmodels.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp


class BudgetCreator : Fragment() {


    private val viewModel : BugdetCreatorViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()

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

            var budgetToCreate = Budget()

            if (true) {//!binding.inputName.text.isNullOrBlank() && !binding.inputAdress.text.isNullOrBlank() && !binding.inputAdress2.text.isNullOrBlank() && !binding.inputPhone.text.isNullOrBlank() && !binding.inputAlternativePhone.text.isNullOrBlank() && !binding.inputExpirationDate.text.isNullOrBlank()){

                var  parcialBudget = Budget("",
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
                    mutableListOf())

                    var action = BudgetCreatorDirections.actionBudgetCreator2ToNewBudgetFragment(parcialBudget)
                binding.root.findNavController().navigate(action)
            }else { Snackbar.make(binding.budgetCreator, "Todos los campos deben tener valores", Snackbar.LENGTH_LONG).show()
                }
            var clientName = binding.inputName.text.toString()
            var adress =  binding.inputAdress.text.toString()
            var betweenStreets = binding.inputAdress2.text.toString()
            var phone = binding.inputPhone.text.toString()
            var alternativePhone = binding.inputAlternativePhone.text.toString()
            var dateExpirate = binding.inputExpirationDate.text.toString()

            sharedViewModel.itemLiveList.observe(viewLifecycleOwner, Observer { itemList ->

                var itemList = itemList

                var budgetToCreate = budgetToCreate

                budgetToCreate.productsItems = itemList

                budgetToCreate.productsItems.toString()


            })

        }

       // viewModel.createBudget(budgetMock)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}