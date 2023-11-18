package tn.esprit.safeguardapplication.models

import androidx.room.Entity

@Entity
data class Cours ( val image: Int ,
              val description: String ,
              val video : Int)

