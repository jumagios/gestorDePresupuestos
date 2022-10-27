package com.example.gestionpresupuesto.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (

    var userID: String,
    var userEmail: String,
    var password: String,
    var dni: String,
    var name: String,
    var isAdmin: Boolean
) : Parcelable {
    constructor() : this ("","","","","",false)
}