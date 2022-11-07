package com.example.gestionpresupuesto.repository

import android.util.Log
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Counter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class BudgetRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

     fun createBudget(budgetToCreate: Budget) {

        try {

            db.collection("budgets").add(budgetToCreate)

        } catch (e: Exception) {
            Log.d("BudgetRepository", e.message.toString())
            throw Exception("Error en la creación del presupuesto")
        }

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

            val data = db.collection("budgets").whereEqualTo("budgetNumber", ID).get().await()

            if(!data.isEmpty) {

                for (document in data) {

                    var budgetFound = document.toObject<Budget>()

                    budgetList.add(budgetFound)

                }

            }

        } catch (e : Exception) {

            Log.d("BudgetRepository", e.message.toString())
        }

        return budgetList
    }

    suspend fun deleteBudget(budgetToDelete : Budget) {

        try{

            val data = db.collection("budgets").whereEqualTo("budgetNumber", budgetToDelete.budgetNumber).get().await()

            if (!data.isEmpty) {

                for (document in data) {

                    var documentID = document.id
                    val data = db.collection("budgets").document(documentID)
                        .update("erased", true)

                }
            }

        } catch (e : Exception) {

            Log.d("BudgetRepository", e.message.toString())
        }
    }

    suspend fun updateBudgetState(budgetToUpdate : Budget) {

        try{

            val data = db.collection("budgets").whereEqualTo("budgetNumber", budgetToUpdate.budgetNumber).get().await()

            if (!data.isEmpty) {

                for (document in data) {

                    var documentID = document.id
                    db.collection("budgets").document(documentID)
                        .update("state", budgetToUpdate.state)

                }
            }

        } catch (e : Exception) {

            Log.d("BudgetRepository", e.message.toString())
        }
    }

         suspend fun getBudgetCounter() : Counter {

            try {

                var counter = db.collection("budgetCounter")
                    .document("counter").get().await()

                var budgetCounter = counter.toObject<Counter>()!!

                return budgetCounter

            } catch (e: Exception) {
                Log.d("BudgetRepository", e.message.toString())
                throw Exception("Error obteniendo el contador de presupuestos")
            }
        }

      fun increaseBudgetCounter(newCounterValue : Int) {

        try {

            val counter = db.collection("budgetCounter").document("counter")
            counter.update("counter", newCounterValue)

        } catch (e: Exception) {
            Log.d("BudgetRepository", e.message.toString())
            throw Exception("Error incrementando el contador de presupuestos")
        }

        }

}

