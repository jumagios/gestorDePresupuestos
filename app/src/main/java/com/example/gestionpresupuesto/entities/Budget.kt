package com.example.gestionpresupuesto.entities

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize


@Parcelize
data class Budget (

    var budgetNumber: String,
    var clientName: String,
    var clientDomicile: String,
    var betweenStreet1: String,
    var betweenStreet2: String,
    var apartment: String,
    var locality: String,
    var province: String,
    var phone : String,
    var alternativePhone : String,
    var budgetDate : Timestamp,
    var expirationDate : String,
    var isErased: Boolean,
    var totalGross : Double,
    var productsItems: MutableList<Item>
) : Parcelable {

    constructor() : this("","","","",
        "","", "","","","",
         Timestamp.now(),"",false,0.0, mutableListOf())

    override fun toString(): String {
        return "Budget(productsItems=$productsItems)"
    }


}




