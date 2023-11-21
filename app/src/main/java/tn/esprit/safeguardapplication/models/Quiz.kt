package tn.esprit.safeguardapplication.models



data class Quiz(
    val id: String,
    val titre: String,
    val questions: List<Questions>
)