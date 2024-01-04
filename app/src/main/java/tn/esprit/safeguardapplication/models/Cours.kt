package tn.esprit.safeguardapplication.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey



data class Cours (
    val _id: String,
    val Type : type,
    val image: String,
    val description: String

)
{
    enum class type {
        Introduction,CAUSE, CONSEQUENCE, SIGNE , Agir
    }




}

