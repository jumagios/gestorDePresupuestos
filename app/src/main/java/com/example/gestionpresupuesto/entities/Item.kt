package com.example.gestionpresupuesto.entities

data class Item(

    var internalProductCode: String,
    var name: String,
    var description: String,
    var price: Double,
    var quantity : Int
)  {
    constructor() : this ("","","",0.0, 0)
}