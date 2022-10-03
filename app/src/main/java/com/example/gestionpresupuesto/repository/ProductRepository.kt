package com.example.gestionpresupuesto.repository

import android.util.Log
import com.example.gestionpresupuesto.entities.Product
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ProductRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun createProduct(productToCreate: Product) {

        try {

            db.collection("products").add(productToCreate)

        } catch (e: Exception) {

            Log.d("ProductRepository", e.message.toString())
            throw Exception("Error en la creación del producto")

        }
    }

    suspend fun getAllProducts(): MutableList<Product> {

        var productList = mutableListOf<Product>()

        try {

            var data = db.collection("products").get().await()

            for (document in data) {

                productList.add(document.toObject<Product>())
            }

        } catch (e: Exception) {

            Log.d("ProductRepository", e.message.toString())

        }

        return productList

    }

    suspend fun findProductByID(ID : String) : MutableList<Product> {

        var productList = mutableListOf<Product>()

        return productList
    }



    fun deleteProduct(productToDelete: Product) : Boolean{

        var result = false

        try {

            db.collection("products").document().update("isErased", true)
            result = true

        } catch (e: Exception) {

            Log.d("ProductRepository", e.message.toString())

        }

        return result
    }
}