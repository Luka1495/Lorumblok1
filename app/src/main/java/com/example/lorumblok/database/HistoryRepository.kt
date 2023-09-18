package com.example.lorumblok.database

import android.content.Context

class HistoryRepository {

    suspend fun getAllGames(context: Context):List<Game>{
        return AppDatabase.getDatabase(context).gameDao().getAllGames()
    }
    suspend fun saveGametoDatabase(game: Game,context: Context){
        AppDatabase.getDatabase(context).gameDao().insertGame(game)
    }
    suspend fun deleteAllGames(context: Context){
        AppDatabase.getDatabase(context).gameDao().deleteAllPreviousGames()
    }
}