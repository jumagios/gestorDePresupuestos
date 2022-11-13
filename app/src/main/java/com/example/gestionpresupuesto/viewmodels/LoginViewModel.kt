package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val userRepository = UserRepository()

    var userList = MutableLiveData<MutableList<User>>()


    fun getUserState(email : String)  {

        viewModelScope.launch(Dispatchers.Main) {

            var userListFound = userRepository.getUserByEmail(email)

            userList.value = userListFound

        }
    }
}

