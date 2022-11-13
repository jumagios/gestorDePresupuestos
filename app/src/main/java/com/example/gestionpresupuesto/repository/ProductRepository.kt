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
    private val ref = db.collection("products").document()

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

                var actualProduct = document.toObject<Product>()
                if(actualProduct.erased == false) {
                    productList.add(actualProduct)
                }


            }

        } catch (e: Exception) {

            Log.d("ProductRepository", e.message.toString())

        }

        return productList

    }

    suspend fun deleteProduct(productToDelete: Product) {

        try{

            val data = db.collection("products").whereEqualTo("firestoreID", productToDelete.firestoreID).get().await()

            if (!data.isEmpty) {

                for (document in data) {

                    var documentID = document.id
                    db.collection("products").document(documentID)
                        .update("erased", true)
                }
            }

        } catch (e : Exception) {

            Log.d("BudgetRepository", e.message.toString())
        }

    }

    suspend fun updateProduct(productToUpdate: Product) {
        try {


            val data = db.collection("products").whereEqualTo("firestoreID", productToUpdate.firestoreID).get().await()



            if (!data.isEmpty) {

                for (document in data) {


                    var documentID = document.id
                    db.collection("products").document(documentID)
                        .update("name", productToUpdate.name,
                            "description", productToUpdate.description,
                            "category", productToUpdate.category,
                            "internalPoruductCode", productToUpdate.internalProductCode,
                            "providerProductCode", productToUpdate.providerProductCode,
                            "price", productToUpdate.price,
                            "stock", productToUpdate.stock)
                }
            }

        } catch (e : Exception) {

            Log.d("ProductRepository", e.message.toString())

        }


    }



    suspend fun findProductByID(ID: String): MutableList<Product> {

        var productList = mutableListOf<Product>()

        try {

            val data = db.collection("products").whereEqualTo("firestoreID", ID).get().await()

            for (document in data) {

                var productFound = document.toObject<Product>()

                if (productFound != null) {
                    productList.add(productFound)
                }

            }
        } catch (e: Exception) {
            Log.d("ProductRepository", e.message.toString())
        }
        return productList
    }


}