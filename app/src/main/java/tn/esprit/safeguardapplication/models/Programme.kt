package tn.esprit.safeguardapplication.models

import androidx.room.Entity

@Entity
data class Programme(
    val _id: String,
    val image: String,
    val Titre: String,
    val descriptionProgramme: String
        )