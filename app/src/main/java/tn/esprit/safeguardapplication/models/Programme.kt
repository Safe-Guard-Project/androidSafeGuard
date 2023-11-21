package tn.esprit.safeguardapplication.models




data class Programme(
    val _id: String,
    val Titre: String,
    val descriptionProgramme: String,
    val image: String,
    val cours: List<Cours>
)
