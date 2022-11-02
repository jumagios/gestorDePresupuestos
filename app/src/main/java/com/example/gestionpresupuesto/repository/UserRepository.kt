package com.example.gestionpresupuesto.repository

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

    suspend fun getAllUsers(): MutableList<User> {

        var userList = mutableListOf<User>()

        try {

            var data = db.collection("users").get().await()

            for (document in data) {

                userList.add(document.toObject<User>())

            }

        } catch (e: Exception) {

            Log.d("UserRepository", e.message.toString())

        }

        return userList

    }

    suspend fun isAdmin(): Boolean {

        var isAdmin = false

        try {

            var actualUserEmail = auth.currentUser?.email.toString()

            if (actualUserEmail != null) {

                var data = db.collection("users").document(actualUserEmail).get().await()

                var actualUser = data.toObject<User>()

                if (actualUser != null) {

                    isAdmin = actualUser.admin
                    return isAdmin
                }
            }


        } catch (e: Exception) {

            Log.d("UserRepository", e.message.toString())

        }

        return isAdmin

    }
}