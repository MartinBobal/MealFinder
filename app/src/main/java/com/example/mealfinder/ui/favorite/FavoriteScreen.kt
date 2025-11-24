package com.example.mealfinder.ui.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = viewModel()
) {

    val favorites by viewModel.favorites.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Oblíbené recepty") })
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                if (favorites.isEmpty()) {
                    item {
                        Text("Zatím nemáš žádné oblíbené recepty.")
                    }
                } else {
                    items(favorites) { item ->
                        Text(
                            text = item,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
