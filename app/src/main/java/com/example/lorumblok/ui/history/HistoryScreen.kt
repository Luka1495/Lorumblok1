package com.example.lorumblok.ui.history

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lorumblok.database.Game

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {
    val context = LocalContext.current
    val historyViewModel: HistoryViewModel = viewModel()
    historyViewModel.getAllGames(context)
    val gameList = historyViewModel.gameList.collectAsState()
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Game history") },
            navigationIcon = { IconButton(onClick = { navController.navigateUp()}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }},
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        )
    }) {
        if(gameList.value.isEmpty()){
            EmptyListComposable()
        }else{
            HistoryContent(gameList.value,it)
        }

    }

}

@Composable
fun HistoryContent(gameList: List<Game>,paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = paddingValues.calculateTopPadding(),
                bottom = 16.dp
            )
    ) {
        items(gameList) {
            SingleGameComposable(game = it)
        }
    }
}