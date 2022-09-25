package com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.BugdetCreatorViewModel

class BudgetCreator : Fragment() {

    companion object {
        fun newInstance() = BudgetCreator()
    }

    private lateinit var viewModel: BugdetCreatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bugdet_creator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BugdetCreatorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}