package com.example.itunesswaran.Model.Repository.Database

import android.app.Application

class ArtistApplication : Application() {

    // No need to cancel this scope as it'll be torn down with the process
//    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { ArtistRoomDatabase.getDatabase(this) }
    val repository by lazy { ArtistRepository(database.wordDao()) }
}