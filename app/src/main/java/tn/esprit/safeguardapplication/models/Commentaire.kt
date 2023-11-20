package tn.esprit.safeguardapplication.models

import androidx.room.Entity

@Entity
data class Commentaire(
    val _id : String ,
    val textComment : String ,
    val idCoursProgramme : String

)
