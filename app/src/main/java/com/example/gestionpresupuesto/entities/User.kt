package com.example.gestionpresupuesto.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    var email: String,
    var dni: String,
    var name: String,
    var password: String,
    var admin: Boolean
    ) : Parcelable {
    constructor() : this ("","","", "",false)
}