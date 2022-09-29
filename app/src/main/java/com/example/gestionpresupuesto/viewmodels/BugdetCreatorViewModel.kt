package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.repository.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BugdetCreatorViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()

    fun createBudget(budgetToCreate: Budget)
    {
        viewModelScope.launch(Dispatchers.Main) {

            budgetRepository.getAccumulator()
            budgetRepository.createBudget(budgetToCreate)

        }
    }
}