package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetCreator
import com.example.gestionpresupuesto.fragments.menu.containerFragmentBudget.BudgetCreatorDirections
import com.example.gestionpresupuesto.repository.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetCreatorViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()

    fun createBudget (budgetToCreate: Budget, fragmet: BudgetCreator) {

        viewModelScope.launch(Dispatchers.Main) {

            try {

                budgetToCreate.budgetNumber = setBudgetNumber()
                budgetToCreate.totalGross = calculateTotalGross(budgetToCreate.productsItems)
                budgetRepository.createBudget(budgetToCreate)
                fragmet.showAlert()



            } catch (e: Exception) {

                Log.d("BugdetCreatorViewModel", e.message.toString())

            }

            }
        }

     suspend fun setBudgetNumber(): String {
        var finalBudgetNumber = ""

        try {

            val budgetNumberCodeStructure = "00000000"

            var counter = budgetRepository.getBudgetCounter().counter

            var size = counter.toString().length

            var budgetNumber = budgetNumberCodeStructure.substring(size)

            finalBudgetNumber = budgetNumber + counter.toString()

            var newCounter = counter?.toInt()?.plus(1)

            if (newCounter != null) {

                budgetRepository.increaseBudgetCounter(newCounter)
            }


        } catch (e: Exception) {

            Log.d("BugdetCreatorViewModel", e.message.toString())

        }

        return finalBudgetNumber

    }

    private fun calculateTotalGross(itemList : MutableList<Item>) : Double {

        var totalGross = 0.0;

        itemList.forEach {totalGross += (it.quantity * it.price)}

        return totalGross

    }
}