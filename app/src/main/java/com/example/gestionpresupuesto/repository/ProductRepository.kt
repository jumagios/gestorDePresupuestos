package com.example.gestionpresupuesto.repository

import android.content.Context
import com.example.gestionpresupuesto.entities.Product
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun createProduct(ID:String, price: Double) : Product{

        var product = Product(ID, price)

        db.collection("products").add(product)

        return product
    }

}