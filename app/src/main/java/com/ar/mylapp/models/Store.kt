package com.ar.mylapp.models

data class Store (
    val storeId: Int,
    val storeName: String,
    val storeEmail: String,
    val storePhone: String,
    val storeLocation: String,

    //TODO: Campos que podriamos agregar!
    //val storeDescription: String,
    //val storeImage: String,
    //val storeRating: Float,
    //val storeReviews: List<Review>,
    //val storeSocialMedia: List<SocialMedia>,
    //val storeWebsite: String,
)