package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainProductListViewModel : ViewModel() {
    var productRepository = ProductRepository()
    var productList = MutableLiveData<MutableList<Product>>()

    fun getAllProducts() {

        viewModelScope.launch(Dispatchers.Main) {

            var firestoreProductList = productRepository.getAllProducts()
            firestoreProductList.reverse() // Luego listar por precio.

            productList.value = firestoreProductList

        }
    }
}