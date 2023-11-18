package tn.esprit.safeguardapplication.models

import androidx.room.Entity

@Entity
data class Choix(
    val id: String,
    val text: String,
    val isCorrect: Boolean
)