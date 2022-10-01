package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.repository.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BugdetCreatorViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()

    fun findBudgetByID(budgetToCreate: Budget) {

        viewModelScope.launch(Dispatchers.Main) {

            var test = budgetRepository.getBudgetByID(budgetToCreate.budgetNumber)
            test.toString()

        }
    }

    fun createBudget(budgetToCreate: Budget)
    {
        viewModelScope.launch(Dispatchers.Main) {

            var budgetFound = budgetRepository.findBudgetByID(budgetToCreate.budgetNumber)

            if(budgetFound != null) {

                budgetRepository.createBudget(budgetToCreate)

            } else{

                throw Exception("Ya exite el presupuesto")

            }

        }
    }
}