package com.example.gestionpresupuesto.repository

import android.content.Context
import android.util.Log
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.entities.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await



class UserRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun createUser(userToCreate: User, context: Context) {

        try {

            if (userToCreate != null) {
                db.collection("users").add(userToCreate)
            }

        } catch (e: Exception) {

            Log.d("UserRepository", e.message.toString())
            throw Exception("Error en la creación del Usuario")

        }
    }

}

