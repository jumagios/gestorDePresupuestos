package com.example.gestionpresupuesto.fragments.menu

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.gestionpresupuesto.R
import com.example.gestionpresupuesto.viewmodels.BudgetViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import java.time.LocalDate

class Budget : Fragment() {

    companion object {
        fun newInstance() = Budget()
    }

    private lateinit var viewModel: BudgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_budget, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetViewModel::class.java)

    }

    override fun onStart() {

        super.onStart()

        viewModel.createBudget(
            "", "", "", "", Timestamp.now(), Timestamp.now(), Timestamp.now(), false,
            Timestamp.now(), "", "", mutableListOf(),
            100.50,
            5.0, 0.0, 95.0

        ) // hay que agregar los parametros que vienen desde la vista
        // tambien hay que pensar bien como se va a armar la parametrizacion, faltan definiciones

        viewModel.getAllBudgets()

        viewModel.budgetList.observe(viewLifecycleOwner, Observer { result ->

        }) // esto es livedata
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BudgetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}