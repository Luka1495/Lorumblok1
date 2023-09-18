package com.example.lorumblok.ui.standings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lorumblok.database.Game
import com.example.lorumblok.database.HistoryRepository
import com.example.lorumblok.database.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StandingsViewModel(private val historyRepository: HistoryRepository= HistoryRepository(), private val userRepository: UserRepository=UserRepository()):ViewModel() {

    private var _firstPlayerPoints = MutableStateFlow(0)
    val firstPlayerPoints: StateFlow<Int> = _firstPlayerPoints
    private var _secondPlayerPoints = MutableStateFlow(0)
    val secondPlayerPoints: StateFlow<Int> = _secondPlayerPoints
    private var _thirdPlayerPoints = MutableStateFlow(0)
    val thirdPlayerPoints: StateFlow<Int> = _thirdPlayerPoints
    private var _fourthPlayerPoints = MutableStateFlow(0)
    val fourthPlayerPoints: StateFlow<Int> = _fourthPlayerPoints

    fun clearStandings() {
        viewModelScope.launch {
            _firstPlayerPoints.value = 0
            _secondPlayerPoints.value = 0
            _thirdPlayerPoints.value = 0
            _fourthPlayerPoints.value = 0
        }
    }

    fun deleteAllGames(context: Context){
        viewModelScope.launch {
            historyRepository.deleteAllGames(context)
        }
    }

    fun getPlayerPoints(context: Context){
        viewModelScope.launch {
            var firstPoints:Int=0
            var secondPoints=0
            var thirdPoints=0
            var fourthPoints=0
            var gameList:List<Game> = emptyList()
            gameList=historyRepository.getAllGames(context)
            if(gameList.isEmpty()){
                _firstPlayerPoints.value = 0
                _secondPlayerPoints.value = 0
                _thirdPlayerPoints.value = 0
                _fourthPlayerPoints.value = 0
            }else{
                gameList.forEach{
                    firstPoints += it.firstPlayerPoints
                    secondPoints += it.secondPlayerPoints
                    thirdPoints += it.thirdPlayerPoints
                    fourthPoints += it.fourthPlayerPoints
                }
                _firstPlayerPoints.value=firstPoints
                _secondPlayerPoints.value=secondPoints
                _thirdPlayerPoints.value=thirdPoints
                _fourthPlayerPoints.value=fourthPoints
            }
        }
    }
    fun logout(){
        viewModelScope.launch {
            userRepository.logoutUser()
        }
    }
}