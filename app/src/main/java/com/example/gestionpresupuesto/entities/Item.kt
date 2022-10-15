package com.example.gestionpresupuesto.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(

    var internalProductCode: String,
    var name: String,
    var description: String,
    var price: Double,
    var quantity : Int

) : Parcelable {
    constructor() : this ("","","",0.0, 0)
}