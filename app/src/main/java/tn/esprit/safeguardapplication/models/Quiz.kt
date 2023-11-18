package tn.esprit.safeguardapplication.models

import androidx.room.Entity

@Entity

data class Quiz(
    val id: String,
    val titre: String,
    val questions: List<Questions>
)