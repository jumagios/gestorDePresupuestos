package com.example.gestionpresupuesto.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionpresupuesto.entities.Product
import com.example.gestionpresupuesto.entities.User
import com.example.gestionpresupuesto.fragments.menu.containerFragmentProduct.ProductCreator
import com.example.gestionpresupuesto.repository.ProductRepository
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductCreatorViewModel : ViewModel() {

    var productRepository = ProductRepository()
    var imagenURL = MutableLiveData<String>()

    fun setImageURL(url : String){

        imagenURL.value = url

    }

    fun createProduct(productToCreate: Product, fragment : ProductCreator) {
        viewModelScope.launch(Dispatchers.Main) {

            try {
                productToCreate.firestoreID = setFirestoreID(productToCreate.internalProductCode)

                var productListFound = productRepository.findProductByID(productToCreate.firestoreID)

                if ( productListFound.size == 0) {

                    productRepository.createProduct(productToCreate)
                    fragment.showAlert()

                } else {

                    throw Exception("Ya existe el producto")

                }

            } catch (e: Exception) {

                Log.d("ProductCreatorViewModel", e.message.toString())

            }
        }
    }
}

    private fun setFirestoreID(internalProductCode : String) : String {

        var firestoreID = internalProductCode.replace("\\s".toRegex(), "").uppercase()

        return firestoreID

}





