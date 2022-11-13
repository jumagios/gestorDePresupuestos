package com.example.gestionpresupuesto.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class UserRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun createUser(userToCreate: User, mutableLiveData: MutableLiveData<String>) {

        db.collection("users").document(userToCreate.email).set(userToCreate)
            .addOnSuccessListener {

                mutableLiveData.postValue("success")
            }
            .addOnFailureListener { exception ->
                println("Error saving document: ${exception.message}")
                mutableLiveData.postValue("Error creating User: ${exception.message}")
            }
    }

    suspend fun getUserByEmail(email: String) : MutableList<User> {

        var userList = mutableListOf<User>()

        try{

            val data = db.collection("users").whereEqualTo("email", email).get().await()

            if(!data.isEmpty) {

                for (document in data) {

                    var userFound = document.toObject<User>()

                    userList.add(userFound)

                }

            }

        } catch (e : Exception) {

            Log.d("UserRepository", e.message.toString())
        }

        return userList

    }

    suspend fun deleteUser(userToDelete: User) {

        try {

            val data =
                db.collection("users").whereEqualTo("email", userToDelete.email).get().await()

            if (!data.isEmpty) {

                for (document in data) {

                    var documentID = document.id
                    db.collection("users").document(documentID)
                        .update("erased", true)
                }
            }

        } catch (e: Exception) {

            Log.d("UserRepository", e.message.toString())
        }

    }


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

     fun updateAdminState(userToUpdate : User) {

        try {
        db.collection("users").document(userToUpdate.email)
                        .update("admin", userToUpdate.admin)

        db.collection("users").document(userToUpdate.email)
                .update("erased", false)


        } catch (e: Exception) {

            Log.d("UserRepository", e.message.toString())

        }
    }
}


