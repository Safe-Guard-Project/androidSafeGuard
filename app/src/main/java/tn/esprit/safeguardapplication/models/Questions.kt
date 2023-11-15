package tn.esprit.safeguardapplication.models
data class Questions(
    val id: String,
    val text: String,
    val choix: List<Choix>
)