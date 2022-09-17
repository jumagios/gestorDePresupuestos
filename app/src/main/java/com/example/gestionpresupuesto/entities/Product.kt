package com.example.gestionpresupuesto.entities

data class Product (
    var ID: String,
    var price: Double,

){

    constructor() : this(
        "",
        0.0,
    )


}