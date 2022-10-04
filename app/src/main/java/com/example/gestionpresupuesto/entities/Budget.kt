package com.example.gestionpresupuesto.entities

import com.google.firebase.Timestamp

data class Budget(

    var budgetNumber: String,
    var clientName: String,
    var clientDomicile: String,
    var betweenStreets: String,
    var apartment: String,
    var flat: String,
    var phone : String,
    var alternativePhone : String,
    var budgetDate : Timestamp,
    var expirationDate : Long,
    var isErased: Boolean,
    var productsItems: MutableList<String>
    ) {

    constructor() : this("","","","",
        "","", "",
        "", Timestamp.now(),0,false, mutableListOf())
}




