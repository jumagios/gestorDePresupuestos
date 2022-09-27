package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.BudgetRepository
import com.example.gestionpresupuesto.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    var productRepository = ProductRepository()
    var productList = MutableLiveData<MutableList<Product>>()

    fun getAllBudgets() {

        viewModelScope.launch(Dispatchers.Main) {

            var firestoreBudgetList = productRepository.getAllProducts()
            firestoreBudgetList.reverse() // para mostrar primero ultima agregada

            productList.value = firestoreBudgetList

        }
    }}