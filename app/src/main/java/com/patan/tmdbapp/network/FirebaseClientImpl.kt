package com.patan.tmdbapp.network

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseClientImpl : FirebaseClient {
    override fun addFavourite(movieId: Int, userEmail: String) {
        val db = Firebase.firestore
        val movieData = hashMapOf(
            "movieId" to movieId
        )
        db.collection("users").document(userEmail ?: "").set(
            mapOf("$movieId" to movieData),
            SetOptions.merge()
        )
    }

    override fun deleteFavourite(movieId: Int, userEmail: String) {
        val db = Firebase.firestore
        val messageRef = db.collection("users").document(userEmail ?: "")
        messageRef.update(mapOf("$movieId" to FieldValue.delete()))
    }


    override fun isFavourite(movieId: Int, userEmail: String, callback: (Boolean) -> Unit) {
        val db = Firebase.firestore
        val docRef = db.collection("users").document(userEmail ?: "")
        docRef.get().addOnSuccessListener { document ->
            if (document.exists() && document.contains("$movieId")) {
                callback(true)
            } else {
                callback(false)
            }
        }
    }
    override fun getMovieId(userEmail: String, callback: (List<String>) -> Unit) {
        val db = Firebase.firestore
        val docRef = db.collection("users").document(userEmail)
        docRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                callback(document.data?.keys?.toList() ?: emptyList())
            } else {
                callback(emptyList())
            }
        }
    }
}