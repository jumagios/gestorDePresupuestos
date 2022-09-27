package com.example.gestionpresupuesto.entities

data class Product (

    var internalProductCode: String,
    var providerProductCode: String,
    var name: String,
    var description: String,
    var category: String,
    var price: Double,
    var stock: Int,
    var creationDate: Long,
    var isErased: Boolean,
    var imageURL: String
){
}