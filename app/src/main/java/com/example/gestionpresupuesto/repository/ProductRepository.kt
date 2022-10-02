package com.example.gestionpresupuesto.repository

import android.content.Context
import android.util.Log
import com.example.gestionpresupuesto.entities.Product
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.Locale.Category

class ProductRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun createProduct(createToProduct: Product) {

        var product = Product()
        try {
        db.collection("products").add(product)
        } catch (e: Exception) {
        Log.d("ProductRepository", e.message.toString())
         }
    }

    /**fun deleteProduct(productToDelete: Product): Product  {

            db.collection("products").document().delete(productToDelete.internalProductCode)
            return Product()
    }*/


    suspend fun updateProduct(productToUpdate: Product) {

            var test = db.collection("products").document("beSDhQUETQIpfrSOxs6w")

            test.set(productToUpdate)

        }

    /* Esto lo probé con Fer y funciona pero es para un dato en particular y harcodeado

            var test = db.collection("products").document("beSDhQUETQIpfrSOxs6w")

            test.update("name", productToUpdate.name )

     */



    /**fun findProductByID(ID: String) : Product {

        var product = db.collection("products").document().get(ID)

        return product

    }*/

    fun getAllProducts() : MutableList<Product> {

        var products = mutableListOf<Product>()

        return products

    }

    fun getProductsByCategory(category: String) : MutableList<Product> {

        var products = mutableListOf<Product>()

        return products

    }

}
