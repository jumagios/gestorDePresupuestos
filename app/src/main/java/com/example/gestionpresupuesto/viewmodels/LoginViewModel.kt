package com.example.gestionpresupuesto.viewmodels

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    //TEST BASE DE DATOS

    var productRepository = ProductRepository()

    fun  createProduct(ID : String, price : Double)  {

        viewModelScope.launch(Dispatchers.Main) {

            productRepository.createProduct(ID, price)
        }
    }
}