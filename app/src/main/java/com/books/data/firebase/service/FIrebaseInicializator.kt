package com.books.data.firebase.service

import android.content.Context
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

fun inicializatorFirebase(context: Context) {
    val options = FirebaseOptions.Builder()
        .setProjectId("books-4ca10")
        .setApplicationId("1:474719311781:android:84fb50456ac6b7ba75ba4c")
        .build()
    try {
        Firebase.initialize(context, options, "bd-firebase")
    } catch (e: Exception) {
    }
}