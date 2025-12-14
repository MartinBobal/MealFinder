package com.example.mealfinder.ui.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    onRandomClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Název aplikace
        Text(
            text = "MealFinder",
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Navigace na náhodný recept
        Button(
            onClick = onRandomClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Náhodný recept")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigace na vyhledávání receptů
        Button(
            onClick = onSearchClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vyhledat recept")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigace na seznam oblíbených receptů
        Button(
            onClick = { navController.navigate("favorite") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Oblíbené recepty")
        }
    }
}
