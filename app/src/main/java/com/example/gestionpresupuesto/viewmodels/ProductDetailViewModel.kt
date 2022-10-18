package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {

    var productRepository = ProductRepository()

    fun updateProduct(productToUpdate: Product) {
        viewModelScope.launch(Dispatchers.Main) {

            try {

                var productListFound = productRepository.findProductByID(productToUpdate.internalProductCode)

                productRepository.updateProduct(productToUpdate)

            } catch (e: Exception) {

                Log.d("ProductCreatorViewModel", e.message.toString())

            }
        }

    }

    fun deleteProduct(productToDelete: Product) {
        viewModelScope.launch(Dispatchers.Main) {

            try {

                productRepository.deleteProduct(productToDelete)

            } catch (e: Exception) {

                Log.d("ProductCreatorViewModel", e.message.toString())

            }
        }

    }
}