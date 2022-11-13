package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel() {


    var userRepository = UserRepository()

   fun updateAdminState(userToUpdate: User) {
        viewModelScope.launch(Dispatchers.Main) {

            try {
                userRepository.updateAdminState(userToUpdate)

            } catch (e: Exception) {

                Log.d("UserCreatorViewModel", e.message.toString())

            }
        }

    }

    fun deleteUser(userToDelete: User) {
        viewModelScope.launch(Dispatchers.Main) {

            try {

                userRepository.deleteUser(userToDelete)

            } catch (e: Exception) {

                Log.d("UserCreatorViewModel", e.message.toString())

            }
        }

    }
}