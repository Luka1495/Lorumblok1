package com.example.lorumblok.ui.newgame

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSpinner(options:List<String>,expanded:MutableState<Boolean>,selectedOptionText:MutableState<String>) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .clickable {
                expanded.value = !expanded.value
            }
            .align(Alignment.Center)) {
            Text(text = selectedOptionText.value)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            DropdownMenu(expanded = expanded.value, onDismissRequest = {
                expanded.value = false
            }) {
                options.forEach {
                    DropdownMenuItem(onClick = {
                        selectedOptionText.value = it
                        expanded.value = false
                    },
                        text = { Text(text = it)},
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    )
                }
            }
        }
    }
}