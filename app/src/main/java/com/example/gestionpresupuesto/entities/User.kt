package com.example.gestionpresupuesto.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    var admin: Boolean,
    var dni: String,
    var email: String,
    var name: String,
    var password: String,
    var erased : Boolean
    ) : Parcelable {
    constructor() : this (false,"","", "","", false)
}