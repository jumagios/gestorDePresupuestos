package com.example.gestionpresupuesto.entities


import com.google.firebase.Timestamp
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (

    var firestoreID: String,
    var internalProductCode: String,
    var providerProductCode: String,
    var name: String,
    var description: String,
    var category: String,
    var price: Double,
    var stock: Int,
    var creationDate: Timestamp,
    var isErased: Boolean,
    var imageURL: String
) : Parcelable {
    constructor() : this ("","","","","","",0.0,0,Timestamp.now(),false, "")
}