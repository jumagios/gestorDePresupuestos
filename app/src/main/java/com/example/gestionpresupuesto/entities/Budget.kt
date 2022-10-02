package com.example.gestionpresupuesto.entities

data class Budget(

    var firestoreID : String,
    var budgetNumber: String,
    var clientName: String,
    var clientDomicile: String,
    var betweenStreets: String,
    var apartment: String,
    var flat: String,
    var phone : String,
    var alternativePhone : String,
    var budgetDate : Long,
    var expirationDate : Long,
    var productsItems: MutableList<String>
    ) {

    constructor() : this("","","","","",
        "","", "",
        "", 0,0, mutableListOf())
}




