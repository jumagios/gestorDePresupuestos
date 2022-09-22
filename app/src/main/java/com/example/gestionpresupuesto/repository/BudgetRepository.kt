package com.example.gestionpresupuesto.repository

import android.util.Log
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Item
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import java.time.LocalDate

class BudgetRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

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

    ): Budget {

        var budgetToCreate = Budget(
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

        try{
            db.collection("budgets").add(budgetToCreate)

        } catch(e : Exception){
            Log.d("BudgetRepository", e.message.toString())
        }


        return budgetToCreate
    }


}