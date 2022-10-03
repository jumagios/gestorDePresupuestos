package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.viewmodels.BugdetCreatorViewModel

class BudgetCreator : Fragment() {


    private lateinit var viewModel: BugdetCreatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bugdet_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BugdetCreatorViewModel::class.java)

    }

    override fun onStart() {

        super.onStart()


        var budgetMock = Budget(
            "", "Groso S.A", "Rivadavia 3456",
            "Senillosa y Colombia", "2B",  "2",
            "1134567528", "",
            26092022,260112022, mutableListOf()
        )

        viewModel.createBudget(budgetMock)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BugdetCreatorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}