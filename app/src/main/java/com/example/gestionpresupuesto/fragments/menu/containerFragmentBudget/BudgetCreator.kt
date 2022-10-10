package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.viewmodels.BugdetCreatorViewModel
import com.google.firebase.Timestamp

class BudgetCreator : Fragment() {


    private lateinit var v: View
    private lateinit var viewModel: BugdetCreatorViewModel
    private lateinit var nextButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_bugdet_creator, container, false)
        nextButton = v.findViewById(R.id.siguiente_button)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BugdetCreatorViewModel::class.java)

    }

    override fun onStart() {

        super.onStart()

        nextButton.setOnClickListener {

            try {

             var action = BudgetCreatorDirections.actionBudgetCreator2ToNewBudgetFragment()
             v.findNavController().navigate(action)

            } catch (e : Exception) {
                e.message.toString()
            }


        }

       // viewModel.createBudget(budgetMock)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BugdetCreatorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}