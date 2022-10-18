package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.BudgetRepository
import com.example.gestionpresupuesto.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetWithItemsDetailsViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()

    fun deleteBudget(budgetToDelete: Budget) {
        viewModelScope.launch(Dispatchers.Main) {

            try {

                budgetRepository.deleteBudget(budgetToDelete)

            } catch (e: Exception) {

                Log.d("BudgetCreatorViewModel", e.message.toString())

            }
        }

    }


}