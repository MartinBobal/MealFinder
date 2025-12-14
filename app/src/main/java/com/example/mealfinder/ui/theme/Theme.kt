package com.example.mealfinder.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Barevné schéma pro tmavý režim
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Barevné schéma pro světlý režim
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun MealFinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Povolení dynamických barev na Androidu 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Výběr barevného schématu podle systému a verze Androidu
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Aplikace globálního Material3 tématu
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
