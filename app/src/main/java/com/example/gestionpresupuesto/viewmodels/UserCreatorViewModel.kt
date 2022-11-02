package com.example.gestionpresupuesto.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth
private var userToCreate = User()

class UserCreatorViewModel : ViewModel() {
        var userRepository = UserRepository()
        var success: MutableLiveData<String> = MutableLiveData()
        fun registerUser(user: User){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email, user.password)
                        .addOnSuccessListener {
                                userRepository.createUser(user, success)

                        }
                        .addOnFailureListener { exception -> success.postValue(exception.message) }
        }
}
