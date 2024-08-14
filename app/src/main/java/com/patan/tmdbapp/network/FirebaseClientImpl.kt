package com.patan.tmdbapp.network

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

class FirebaseClientImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirebaseClient {

    override fun addFavourite(movieId: Int, userEmail: String) {
        val movieData = hashMapOf("movieId" to movieId)
        firestore.collection("users").document(userEmail).set(
            mapOf("$movieId" to movieData), SetOptions.merge()
        )
    }

    override fun deleteFavourite(movieId: Int, userEmail: String) {
        val messageRef = firestore.collection("users").document(userEmail)
        messageRef.update(mapOf("$movieId" to FieldValue.delete()))
    }

    override fun isFavourite(movieId: Int, userEmail: String, callback: (Boolean) -> Unit) {
        val docRef = firestore.collection("users").document(userEmail)
        docRef.get().addOnSuccessListener { document ->
            callback(document.exists() && document.contains("$movieId"))
        }
    }

    override fun getMovieId(userEmail: String, callback: (List<String>) -> Unit) {
        val docRef = firestore.collection("users").document(userEmail)
        docRef.get().addOnSuccessListener { document ->
            callback(document.data?.keys?.toList() ?: emptyList())
        }
    }

    override fun addComment(movieName: String, userEmail: String, comment: String) {
        val movieData = hashMapOf("comment" to comment)
        firestore.collection("comment").document(movieName).set(
            mapOf(userEmail to movieData), SetOptions.merge()
        )
    }

    override fun getComment(movieName: String, callback: (List<String>, List<String>) -> Unit) {
        val docRef = firestore.collection("comment").document(movieName)
        docRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val keys = document.data?.keys?.toList() ?: emptyList()
                val comments = document.data?.mapNotNull { entry ->
                    val value = entry.value
                    if (value is Map<*, *>) {
                        value["comment"] as? String
                    } else {
                        value as? String
                    }
                } ?: emptyList()
                callback(keys, comments)
            } else {
                callback(emptyList(), emptyList())
            }
        }
    }
}