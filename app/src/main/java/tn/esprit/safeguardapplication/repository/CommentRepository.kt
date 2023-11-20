package tn.esprit.safeguardapplication.repository

import retrofit2.Response
import tn.esprit.safeguardapplication.models.Commentaire

interface CommentRepository {
    fun addComment(commentaire: Commentaire): Response<Commentaire>
}