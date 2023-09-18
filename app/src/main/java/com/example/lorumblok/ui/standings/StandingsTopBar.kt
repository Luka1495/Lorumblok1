package com.example.lorumblok.ui.standings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lorumblok.R
import com.example.lorumblok.ui.theme.LorumBlokTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandingsTopBar(onAddClick:()->Unit,onHistoryClick:()->Unit,onPersonClick:()->Unit) {
    TopAppBar(
        title = { Text(text = "Lorum", color = MaterialTheme.colorScheme.onPrimary)},
        actions = {
            IconButton(onClick = onPersonClick) {
                Icon(painter = painterResource(id = R.drawable.baseline_person_24), contentDescription = null,tint = MaterialTheme.colorScheme.onPrimary)
            }
            IconButton(onClick = onAddClick) {
                Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
            }
            IconButton(onClick = onHistoryClick) {
                Icon(painter = painterResource(id = R.drawable.baseline_history_24), contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Composable
@Preview
fun TopBarPreview() {
    LorumBlokTheme {
        StandingsTopBar(onAddClick = { /*TODO*/ },{}) {
            
        }
    }
}