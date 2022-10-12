package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.BudgetWithItemsDetailsViewModel

class BudgetWithItemsDetailsFragment : Fragment() {

    lateinit var v : View
    private lateinit var viewModel: BudgetWithItemsDetailsViewModel
    private lateinit var budgetNumber: TextView
    private lateinit var budgetDate: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_budget_with_items_details, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetWithItemsDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        var budgetDetails = BudgetWithItemsDetailsFragmentArgs.fromBundle(requireArguments()).budgetDetails


        budgetNumber = v.findViewById(R.id.budget_details_budgetnumber)
        budgetNumber.text = budgetDetails.budgetNumber

        budgetDate = v.findViewById(R.id.budget_details_creation_date)
        budgetDate.text = budgetDetails.budgetDate.toDate().toString()




    }

    fun test() {

    }

}