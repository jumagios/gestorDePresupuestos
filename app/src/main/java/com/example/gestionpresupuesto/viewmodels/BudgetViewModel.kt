package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Item
import com.example.gestionpresupuesto.repository.BudgetRepository
import com.google.firebase.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class BudgetViewModel : ViewModel() {

    var budgetRepository = BudgetRepository()
    var budgetList = MutableLiveData<MutableList<Budget>>()

    fun createBudget(
        clientName: String,
        clientDomicile: String,
        betweenStreets: String,
        phone: String,
        budgetDate: Timestamp,
        expirationDate: Timestamp,
        creationDate: Timestamp,
        isDeleted: Boolean,
        deletedDate: Timestamp,
        userID: String,
        userType: String,
        productsItems: MutableList<Item>,
        totalPrice: Double,
        discountPercentage: Double,
        currency: Double,
        profitPercentage: Double
    ) {

        viewModelScope.launch(Dispatchers.Main) {


            budgetRepository.createBudget(
                clientName,
                clientDomicile,
                betweenStreets,
                phone,
                budgetDate,
                expirationDate,
                creationDate,
                isDeleted,
                deletedDate,
                userID,
                userType,
                productsItems,
                totalPrice,
                discountPercentage,
                currency,
                profitPercentage
            )
        }
    }


    fun getAllBudgets() {

        viewModelScope.launch(Dispatchers.Main) {

            var firestoreBudgetList = budgetRepository.getAllBudgets()
            firestoreBudgetList.reverse() // para mostrar primero ultima agregada

            budgetList.value = firestoreBudgetList

        }
    }
}