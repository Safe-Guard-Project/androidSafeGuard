package tn.esprit.safeguardapplication.models

data class TarjetSecurise(
    val id : Int,
    val etat: Boolean,
    val iduser: String,
    val idCatastrophe: String

)
