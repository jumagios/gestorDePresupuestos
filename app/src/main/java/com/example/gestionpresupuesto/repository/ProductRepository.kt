package com.example.gestionpresupuesto.repository

import android.content.Context
import android.util.Log
import com.example.gestionpresupuesto.entities.Product
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
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
    /**fun updateProduct(productToUpdate: Product): String {
            db.collection("products").document().update(productToUpdate.internalProductCode)

            return "El producto fue actualizado exitosamente"
    }*/

    /**fun findProductByID(ID: String) : Product {

        var product = db.collection("products").document().get(ID)

        return product

    }*/

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

    fun getProductsByCategory(category: String) : MutableList<Product> {

        var products = mutableListOf<Product>()

        return products

    }

}
