package com.example.lorumblok.ui.history

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorumblok.database.Game
import com.example.lorumblok.database.HistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyRepository: HistoryRepository= HistoryRepository()):ViewModel() {

    private var _gameList:MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val gameList=_gameList.asStateFlow()

    fun getAllGames(context: Context){
        viewModelScope.launch {
            _gameList.value=historyRepository.getAllGames(context)
        }
    }
}