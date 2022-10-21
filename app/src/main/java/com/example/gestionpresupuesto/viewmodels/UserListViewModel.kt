package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    var userRepository = UserRepository()

    var userList = MutableLiveData<MutableList<User>>()

    fun getAllUsers() {

        viewModelScope.launch(Dispatchers.Main) {

            var firestoreUserList = userRepository.getAllUsers()

            userList.value = firestoreUserList

        }

    }

}