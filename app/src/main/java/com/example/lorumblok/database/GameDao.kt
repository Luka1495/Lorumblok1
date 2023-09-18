package com.example.lorumblok.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Query("SELECT * from Game")
    suspend fun getAllGames():List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE  from Game")
    suspend fun deleteAllPreviousGames()
}