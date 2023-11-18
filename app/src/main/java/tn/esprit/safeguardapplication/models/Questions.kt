package tn.esprit.safeguardapplication.models

import androidx.room.Entity

@Entity
data class Questions(
    val id: String,
    val text: String,
    val choix: List<Choix>
)