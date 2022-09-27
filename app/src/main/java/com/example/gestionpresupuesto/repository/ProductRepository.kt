package com.example.gestionpresupuesto.repository

import android.content.Context
import android.util.Log
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct.ProductCreator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Locale.Category

class ProductRepository {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun createProduct(productToCreate: Product): Product {

        try {
            db.collection("products").add(productToCreate)

        } catch (e: Exception) {
            Log.d("ProductRepository", e.message.toString())
        }

        return productToCreate
    }

    fun deleteProduct(productToDelete: Product): Product  {
        try {
            //db.collection("products").delete(productToDelete)

        } catch (e: Exception) {
            Log.d("ProductRepository", e.message.toString())
        }

        return productToDelete
    }

    fun updateProduct() {

    }

    fun findProductByID(ID: String) : Product {

        var product = Product()

        return product

    }

    fun getAllProducts() : MutableList<Product> {

        var products = mutableListOf<Product>()

        return products

    }

    fun getProductsByCategory(category: String) : MutableList<Product> {

        var products = mutableListOf<Product>()

        return products

    }

}

