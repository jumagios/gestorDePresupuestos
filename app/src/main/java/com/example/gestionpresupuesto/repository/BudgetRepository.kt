package com.example.gestionpresupuesto.repository

import android.util.Log
import com.example.gestionpresupuesto.entities.Budget
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class BudgetRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun createBudget(budgetToCreate: Budget): Budget {

        try {
            db.collection("budgets").document(budgetToCreate.budgetNumber).set(budgetToCreate)

        } catch (e: Exception) {
            Log.d("BudgetRepository", e.message.toString())
        }

        return budgetToCreate
    }

    suspend fun getAllBudgets(): MutableList<Budget> {

        var budgetList = mutableListOf<Budget>()

        try {

            var data = db.collection("budgets").get().await()

            for (document in data) {

                budgetList.add(document.toObject<Budget>())
            }

        } catch (e: Exception) {

            Log.d("BudgetRepository", e.message.toString())

        }

        return budgetList

    }

    suspend fun findBudgetByID(ID : String) : MutableList<Budget> {

        var budgetList = mutableListOf<Budget>()

        try{

            val data = db.collection("budgets").document(ID).get().await()

            var budgetFound = data.toObject<Budget>()

            if(budgetFound != null) {

                budgetList.add(budgetFound)

            }

        } catch (e : Exception) {

            Log.d("BudgetRepository", e.message.toString())
        }

        return budgetList
    }
}




