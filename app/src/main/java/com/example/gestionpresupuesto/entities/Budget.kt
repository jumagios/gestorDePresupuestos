package com.example.gestionpresupuesto.entities

import android.os.Parcelable
import androidx.annotation.DoNotInline
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize


@Parcelize
data class Budget (

    var budgetNumber: String,
    var clientName: String,
    var clientDNI_CUIT : String,
    var clientDomicile: String,
    var betweenStreet1: String,
    var betweenStreet2: String,
    var apartment: String,
    var locality: String,
    var province: String,
    var phone : String,
    var alternativePhone : String,
    var budgetDate : String,
    var budgetCreationHour: String,
    var expirationDate : String,
    var erased: Boolean,
    var totalGross : Double,
    var state : String,
    var productsItems: MutableList<Item>
) : Parcelable {

    constructor() : this("","","","","",
        "","", "","","","",
         "", "","",false,0.0,"pending", mutableListOf())
}




