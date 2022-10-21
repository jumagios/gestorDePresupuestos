package com.example.gestionpresupuesto.entities

data class User (

    var name: String,
    var dni : String,
    var email : String,
    var admin: Boolean

)  {

    constructor() : this ("","","", false)
}
