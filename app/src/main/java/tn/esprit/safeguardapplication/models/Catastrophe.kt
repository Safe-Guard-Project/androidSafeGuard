package tn.esprit.safeguardapplication.models

import java.util.Date

data class Catastrophe(
    val _id: String,
    val titre: String,
    val type: String,
    val tsunami: Int,
    val description: String,
    val date: Date,
    val radius: Double,
    val magnitude: Double,
    val latitudeDeCatastrophe: Double,
    val longitudeDeCatastrophe: Double,
    val createdAt: Date,
    val updatedAt: Date
)