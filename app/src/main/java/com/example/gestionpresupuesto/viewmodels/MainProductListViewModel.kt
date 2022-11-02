package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.ProductRepository
import com.example.gestionpresupuesto.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainProductListViewModel : ViewModel() {
    var productRepository = ProductRepository()
    var productList = MutableLiveData<MutableList<Product>>()
    var isAdmin = MutableLiveData<Boolean>()

    var userRepository = UserRepository()


    fun getAllProducts() {

        viewModelScope.launch(Dispatchers.Main) {

            var firestoreProductList = productRepository.getAllProducts()
            firestoreProductList.reverse() // Luego listar por precio.

            productList.value = firestoreProductList

        }
    }

    fun getIsAdmin() {

        viewModelScope.launch(Dispatchers.Main) {
            isAdmin.value =  userRepository.isAdmin()
        }



    }
}