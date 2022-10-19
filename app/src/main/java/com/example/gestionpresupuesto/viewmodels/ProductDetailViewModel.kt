package com.example.gestionpresupuesto.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {

    var updatedButton : String = "Actualizar"
    var snackbarTxt : String = "Ahora ya podes editar los campos"

    fun getUpdatedTxt () : String {
        return updatedButton
    }

    fun getSnackbarText () : String {
        return snackbarTxt
    }

    var productRepository = ProductRepository()

    fun updateProduct(productToUpdate: Product) {
        viewModelScope.launch(Dispatchers.Main) {

            try {


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