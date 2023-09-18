package com.example.lorumblok.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val firstPlayerPoints:Int,
    val secondPlayerPoints:Int,
    val thirdPlayerPoints:Int,
    val fourthPlayerPoints:Int,
    val gamesPlayed:String
)
