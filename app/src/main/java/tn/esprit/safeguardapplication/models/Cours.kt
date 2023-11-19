package tn.esprit.safeguardapplication.models

import androidx.room.Entity

@Entity
data class Cours (
    val _id: String,
    val Type : type ,
    val image: String ,
    val description: String ,
    )
{
    enum class type {
        Introduction,CAUSE, CONSEQUENCE, SIGNE , Agir
    }
}

