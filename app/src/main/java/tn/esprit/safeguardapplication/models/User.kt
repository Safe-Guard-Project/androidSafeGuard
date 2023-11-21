package tn.esprit.safeguardapplication.models
enum class UserRole {
    CLIENT,
    ADMIN
}
data class User (
    val UserName: String,
    val id:Int,
    val email:String,
    val password: String,
    val Role: Enum<UserRole>,
    val latitudeUser : Int,
    val longitudeUser: Int,
    val numeroTel: Int
    )