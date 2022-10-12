package com.example.gestionpresupuesto.entities

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize


@Parcelize
data class Budget (

    var budgetNumber: String,
    var clientName: String,
    var clientDomicile: String,
    var betweenStreets: String,
    var apartment: String,
    var flat: String,
    var phone : String,
    var alternativePhone : String,
    var budgetDate : Timestamp,
    var expirationDate : String,
    var isErased: Boolean,
    var productsItems: MutableList<Item>
    ) : Parcelable {

    constructor() : this("","","","",
        "","", "",
        "", Timestamp.now(),"",false, mutableListOf())
}




