package com.example.lorumblok.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lorumblok.database.Game
import com.example.lorumblok.ui.standings.PlayerPointsComponents
import com.example.lorumblok.ui.theme.LorumBlokTheme

@Composable
fun SingleGameComposable(game: Game) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            SmallerPlayerPointsComponents(playerName = "Player1", playerPoints = game.firstPlayerPoints)
            SmallerPlayerPointsComponents(playerName = "Player2", playerPoints = game.secondPlayerPoints)
            SmallerPlayerPointsComponents(playerName = "Player3", playerPoints = game.thirdPlayerPoints)
            SmallerPlayerPointsComponents(playerName = "Player4", playerPoints = game.fourthPlayerPoints)
        }
        Text(text = "Game played: ${game.gamesPlayed}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Divider(modifier = Modifier.height(1.dp), color = MaterialTheme.colorScheme.onSurface)

    }
}
@Composable
private fun SmallerPlayerPointsComponents(playerName: String, playerPoints: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = playerName,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = playerPoints.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
@Preview
fun GamePreview() {
    LorumBlokTheme {
        SingleGameComposable(game = Game(firstPlayerPoints = 0, secondPlayerPoints = 0, thirdPlayerPoints = 0, fourthPlayerPoints = 0, gamesPlayed = "što više"))
    }
}