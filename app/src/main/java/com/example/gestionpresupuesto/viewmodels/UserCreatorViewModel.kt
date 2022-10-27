package com.example.gestionpresupuesto.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth
private var userToCreate = User()

class UserCreatorViewModel : ViewModel() {
        var userRepository = UserRepository()
        var userToInsert = User()
        {
                if (!userToCreate.userEmail.isNullOrEmpty() && !userToCreate.dni.isNullOrEmpty() &&
                        !userToCreate.name.isNullOrEmpty() && !userToCreate.password.isNullOrEmpty()) {
                  FirebaseAuth.getInstance().createUserWithEmailAndPassword(userToCreate.userEmail, userToCreate.password)
                          .addOnSuccessListener {
                                  val id = FirebaseAuth.getInstance().currentUser?.email.toString()
                                  val account = userRepository.createUser(userToCreate, )
                                  val accountId = userID
                                  userRepository.createUser(userToCreate, success)
                          }

                }

                }
}
