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

    suspend fun findProductByID(ID : String) : MutableList<Product> {

        var productList = mutableListOf<Product>()

        try{

            val data = db.collection("products").whereEqualTo("firestoreID", ID).get().await()

            for (document in data) {

                var productFound = document.toObject<Product>()

                if(productFound != null) {
                    productList.add(productFound)
                }

            }

        } catch (e : Exception) {
            Log.d("ProductRepository", e.message.toString())
        }

        return productList

    }
}

/**fun findProductByID(ID: String) : Product {

var product = db.collection("products").document().get(ID)

return product

}*/

fun getAllProducts(): MutableList<Product> {

    return mutableListOf()

}

    fun getProductsByCategory(category: String) : MutableList<Product> {

        var products = mutableListOf<Product>()

        return products

    }
