package tn.esprit.safeguardapplication.models

import androidx.room.Entity

@Entity
data class Information(

    val titre: String,
    val typeCatastrophe: String,
    val idUser: String,
    val pays: String,
    val region: String,
    val descriptionInformation: String,
    val image: Int,
    val percentageLiabilities: Int,
    val etat: Int
)

