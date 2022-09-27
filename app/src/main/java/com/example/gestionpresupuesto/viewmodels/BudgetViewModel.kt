package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.repository.BudgetRepository
import com.google.firebase.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()
    var budgetList = MutableLiveData<MutableList<Budget>>()

    fun getAllBudgets() {

        viewModelScope.launch(Dispatchers.Main) {

            var firestoreBudgetList = budgetRepository.getAllBudgets()
            firestoreBudgetList.reverse() // para mostrar primero ultima agregada

            budgetList.value = firestoreBudgetList

        }
    }
}