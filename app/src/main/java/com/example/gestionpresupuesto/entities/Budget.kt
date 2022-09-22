package com.example.gestionpresupuesto.entities

import com.google.firebase.Timestamp
import com.google.type.DateTime
import java.time.LocalDate

data class Budget(

    var clientName: String,
    var clientDomicile: String,
    var betweenStreets: String,
    var phone : String,
    var budgetDate : Timestamp,
    var expirationDate : Timestamp,
    var creationDate : Timestamp,
    var isDeleted : Boolean,
    var deletedDate : Timestamp,
    var userID : String,
    var userType : String,
    var productsItems: MutableList<Item>,
    var totalPrice: Double,
    var discountPercentage: Double,
    var currency: Double,
    var profitPercentage: Double
    ){

    constructor() : this(
        "",
        "",
        "",
        "",
        Timestamp.now(),
        Timestamp.now(),
        Timestamp.now(),
        false,
        Timestamp.now(),
        "",
        "",
        mutableListOf(),
        0.0,
        0.0,
        0.0,
        0.0,




    )


}




