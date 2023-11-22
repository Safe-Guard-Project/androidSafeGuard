package tn.esprit.safeguardapplication.models


import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


enum class UserRole {
    CLIENT,
    ADMIN
}


data class User(
    val UserName: String,
    val id: String,
    val email: String,
    val password: String,
    val Role: Enum<UserRole>,
    val latitudeUser: Int,
    val longitudeUser: Int,
    val numeroTel: String,
    @SerializedName("Token")
    val Token: String
) {
    fun extractUserIdFromToken(): String {
        // JWT Token consists of three parts: header, payload, and signature
        // They are separated by dots. We need the payload (middle part)
        val parts = Token.split(".")


        // Decode the payload
        val payload = parts[1]
        val payloadJson =
            android.util.Base64.decode(payload, android.util.Base64.URL_SAFE)
                .toString(charset("UTF-8"))


        // Parse the JSON payload and extract the user ID
        val jsonObject = JsonObject()
        jsonObject.addProperty(
            "_id",
            Gson().fromJson(payloadJson, JsonObject::class.java).getAsJsonPrimitive("_id").asString
        )


        return jsonObject.get("_id").asString
    }
}
