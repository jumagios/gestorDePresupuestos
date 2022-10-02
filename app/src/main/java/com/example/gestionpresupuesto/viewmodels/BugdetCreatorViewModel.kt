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

                var budgetListFound = budgetRepository.findBudgetByID(budgetToCreate.firestoreID)

                if ( budgetListFound.size == 0) {

                    budgetToCreate.budgetNumber = budgetRepository.getAllBudgets().size.toString()
                    budgetToCreate.firestoreID = setFirestoreID(budgetToCreate.budgetNumber)
                    budgetRepository.createBudget(budgetToCreate)

                } else {

                    throw Exception("Ya existe el presupuesto")

                }

            } catch (e: Exception) {

                Log.d("BugdetCreatorViewModel", e.message.toString())

            }
        }
    }

    private fun setFirestoreID(budgetNumber : String) : String {

        var firestoreID = budgetNumber.replace("\\s".toRegex(), "").uppercase()

        return firestoreID

    }
}

