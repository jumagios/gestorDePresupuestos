package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel() {

    var updatedButton : String = "EDITAR"
    var editableTxt : String = "Ahora ya podes editar los campos"
    var nullOrBlankText : String = "Ningun campo puede quedar en blanco"

    fun getUpdatedTxt () : String {
        return updatedButton
    }

    fun getEditableText () : String {
        return editableTxt
    }


    fun getNullOrBlankTxt(): String {
        return nullOrBlankText
    }

    var userRepository = UserRepository()

/*    fun editUser(userToUpdate: User) {
        viewModelScope.launch(Dispatchers.Main) {

            try {
                userRepository.editUser(userToUpdate)
            } catch (e: Exception) {

                Log.d("UserCreatorViewModel", e.message.toString())

            }
        }

    }
*/
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