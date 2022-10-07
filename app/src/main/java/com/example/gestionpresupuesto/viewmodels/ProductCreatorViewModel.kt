package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Budget
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductCreatorViewModel : ViewModel() {

    var productRepository = ProductRepository()

    fun createProduct(ProductToCreate: Product)
    {
        viewModelScope.launch(Dispatchers.Main) {

            productRepository.createProduct(ProductToCreate)

        }
    }

    fun updateProduct(productMock: Product) {
        viewModelScope.launch(Dispatchers.Main) {

            try {

                var productListFound = productRepository.findProductByID(productMock.internalProductCode)

                productRepository.updateProduct(productMock)

            } catch (e: Exception) {

                Log.d("ProductCreatorViewModel", e.message.toString())

            }
        }
    }
    }