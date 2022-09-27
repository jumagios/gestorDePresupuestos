package com.example.gestionpresupuesto.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.BudgetRepository
import com.example.gestionpresupuesto.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductCreatorViewModel : ViewModel() {
    var productRepository = ProductRepository()

    fun createBudget(ProductToCreate: Product)
    {
        viewModelScope.launch(Dispatchers.Main) {

            productRepository.createProduct(ProductToCreate)

        }
    }
}