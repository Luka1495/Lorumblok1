package com.example.lorumblok.ui.standings

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lorumblok.MainViewModel
import com.example.lorumblok.ui.NavRoutes
import com.example.lorumblok.ui.theme.LorumBlokTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun StandingsScreen(navController: NavController) {
    val standingsViewModel:StandingsViewModel= viewModel()
    val context= LocalContext.current
    standingsViewModel.getPlayerPoints(context)
    val notificationService=NotificationService(context)
    val firstPoints=standingsViewModel.firstPlayerPoints.collectAsState()
    val secondPoints = standingsViewModel.secondPlayerPoints.collectAsState()
    val thirdPoints=standingsViewModel.thirdPlayerPoints.collectAsState()
    val fourthPoints=standingsViewModel.fourthPlayerPoints.collectAsState()
    val showNotification = remember{
        mutableStateOf(false)
    }
    val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    LaunchedEffect(true){
        permissionState.launchPermissionRequest()
    }
    if(permissionState.status.isGranted){
        showNotification.value=true
    }else{
        LaunchedEffect(key1 = true){
            permissionState.launchPermissionRequest()
        }
    }


    Scaffold(topBar =
    {
        StandingsTopBar(
            onAddClick = {
                standingsViewModel.clearStandings()
                standingsViewModel.deleteAllGames(context)
                if (showNotification.value){
                    notificationService.showBasicNotification()
                }
                         },
            onHistoryClick = {navController.navigate(NavRoutes.HISTORY.route)},
            onPersonClick = {
                standingsViewModel.logout()
                navController.navigate(NavRoutes.LOGIN.route){
                popUpTo(NavRoutes.STANDINGS.route){
                    inclusive=true
                }
            } }
        )
    },
        modifier = Modifier.fillMaxSize()
    ) {
        StandingsScreenContent(
            playerOneName = "Player1",
            playerTwoName = "Player2",
            playerThreeName = "Player3",
            playerFourName = "Player4",
            playerOnePoints = firstPoints.value,
            playerTwoPoints = secondPoints.value,
            playerThreePoints = thirdPoints.value,
            playerFourPoints = fourthPoints.value,
            onAddNewGameClick={navController.navigate(NavRoutes.NEW_GAME.route)},
            paddingValues = it
        )
    }
}

@Composable
fun StandingsScreenContent(
    playerOneName: String,
    playerTwoName: String,
    playerThreeName: String,
    playerFourName: String,
    playerOnePoints: Int,
    playerTwoPoints: Int,
    playerThreePoints: Int,
    playerFourPoints: Int,
    onAddNewGameClick:()->Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ), verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PlayerPointsComponents(playerName = playerOneName, playerPoints = playerOnePoints)
            PlayerPointsComponents(playerName = playerTwoName, playerPoints = playerTwoPoints)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PlayerPointsComponents(playerName = playerThreeName, playerPoints = playerThreePoints)
            PlayerPointsComponents(playerName = playerFourName, playerPoints = playerFourPoints)
        }
        Spacer(modifier = Modifier.weight(1F))
        Button(onClick = onAddNewGameClick, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Add new game")
        }
    }
}

@Composable
@Preview
fun StandingsScreenPreview() {
    LorumBlokTheme {
        //StandingsScreen()
    }
}