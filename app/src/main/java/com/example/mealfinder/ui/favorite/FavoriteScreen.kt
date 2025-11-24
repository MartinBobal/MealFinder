package com.example.mealfinder.ui.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    favoritesViewModel: FavoriteViewModel,
    onMealClick: (String) -> Unit
) {
    val favorites by favoritesViewModel.favorites.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Oblíbené recepty") })
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {

            if (favorites.isEmpty()) {
                Text(
                    "Zatím nemáš žádné oblíbené recepty.",
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                val list = favorites.toList()

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(list) { (id, name) ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onMealClick(id) }
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(name)
                            Button(
                                onClick = { favoritesViewModel.removeFavorite(id) }
                            ) {
                                Text("X")
                            }
                        }

                        Divider()
                    }
                }
            }
        }
    }
}
