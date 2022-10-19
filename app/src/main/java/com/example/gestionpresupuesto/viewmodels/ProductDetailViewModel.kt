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