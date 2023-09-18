package com.example.lorumblok.ui.newgame

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorumblok.database.Game
import com.example.lorumblok.database.HistoryRepository
import kotlinx.coroutines.launch

class NewGameViewModel(private val historyRepository: HistoryRepository= HistoryRepository()):ViewModel() {
    fun saveGametoDatabase(
        firstPoints: Int,
        secondPoints: Int,
        thirdPoints: Int,
        fourthPoints: Int,
        gamePlayed: String,
        context: Context
    ) {
        viewModelScope.launch {
            historyRepository.saveGametoDatabase(
                Game(
                    firstPlayerPoints = firstPoints,
                    secondPlayerPoints = secondPoints,
                    thirdPlayerPoints = thirdPoints,
                    fourthPlayerPoints = fourthPoints,
                    gamesPlayed = gamePlayed
                ), context
            )
        }
    }
}