package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.repository.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BugdetCreatorViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()

    fun createBudget(budgetToCreate: Budget) {
        viewModelScope.launch(Dispatchers.Main) {

            try {

                var budgetListFound = budgetRepository.findBudgetByID(budgetToCreate.budgetNumber)

                if (budgetListFound.size == 0) {

                    budgetRepository.createBudget(budgetToCreate)

                } else {

                    throw Exception("Ya exite el presupuesto")

                }

            } catch (e: Exception) {

                Log.d("BugdetCreatorViewModel", e.message.toString())

            }
        }
    }
}

