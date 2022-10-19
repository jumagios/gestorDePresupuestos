package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.repository.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetCreatorViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()

    fun createBudget(budgetToCreate: Budget) {
        viewModelScope.launch(Dispatchers.Main) {

            try {

                budgetToCreate.budgetNumber = setBudgetNumber(budgetRepository.getAllBudgets().size)
                budgetRepository.createBudget(budgetToCreate)

            } catch (e: Exception) {

                Log.d("BugdetCreatorViewModel", e.message.toString())

            }
        }
    }

    private fun setBudgetNumber(budgetListSize : Int) : String {

        val budgetNumberCodeStructure = "00000000"

        var size = budgetListSize.toString().length

        var budgetNumber = budgetNumberCodeStructure.substring(size)

        var finalBudgetNumber = budgetNumber + budgetListSize.toString()

        return finalBudgetNumber

    }
}


