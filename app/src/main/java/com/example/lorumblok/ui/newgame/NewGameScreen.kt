package com.example.lorumblok.ui.newgame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lorumblok.ui.NavRoutes
import com.example.lorumblok.ui.theme.LorumBlokTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewGameScreen(navController: NavController) {
    val newGameViewModel:NewGameViewModel= viewModel()
    val context= LocalContext.current
    var firstInput by remember {
        mutableStateOf("")
    }
    var secondInput by remember {
        mutableStateOf("")
    }
    var thirdInput by remember {
        mutableStateOf("")
    }
    var fourthInput by remember {
        mutableStateOf("")
    }
    val options = listOf("Što više", "Što manje", "Dame", "Crvene", "Crveni kralj i zadnji štih")
    val expanded = remember { mutableStateOf(false) }
    val selectedOptionText = remember { mutableStateOf(options[0]) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Odaberite tip igre koji je odigran:")
        CustomSpinner(options,expanded,selectedOptionText)
        Text(text = "Unosite bodove svih igrača za ovu igru")
        OutlinedTextField(
            value = firstInput,
            onValueChange = { firstInput = it },
            label = { Text(text = "Bodovi prvog igrača") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = secondInput,
            onValueChange = { secondInput = it },
            label = { Text(text = "Bodovi drugog igrača") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = thirdInput,
            onValueChange = { thirdInput = it },
            label = { Text(text = "Bodovi trećeg igrača") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = fourthInput,
            onValueChange = { fourthInput = it },
            label = { Text(text = "Bodovi četvrtog igrača") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.weight(1F))
        Button(onClick = {
            newGameViewModel.saveGametoDatabase(firstInput.toInt(),secondInput.toInt(),thirdInput.toInt(),fourthInput.toInt(),selectedOptionText.value,context)
            navController.navigate(NavRoutes.STANDINGS.route){
                popUpTo(NavRoutes.NEW_GAME.route){
                    inclusive=true
                }
            }
                         }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Spremi igru")
        }
    }
}

@Composable
@Preview
fun NewGameScreenPreview() {
    LorumBlokTheme {
        //NewGameScreen()
    }
}