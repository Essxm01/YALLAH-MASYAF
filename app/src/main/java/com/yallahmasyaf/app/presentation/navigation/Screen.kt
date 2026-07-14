package com.yallahmasyaf.app.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Explore : Screen("explore")
    object Details : Screen("details/{unitId}") {
        fun createRoute(unitId: String): String = "details/$unitId"
    }
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
    object AddUnit : Screen("add_unit")

    companion object {
        // Defines the list of screens to display in the bottom navigation bar
        val bottomNavItems = listOf(
            BottomNavItem(
                route = Explore.route,
                title = "الاستكشاف",
                icon = Icons.Default.Search
            ),
            BottomNavItem(
                route = Favorites.route,
                title = "المفضلة",
                icon = Icons.Default.FavoriteBorder
            ),
            BottomNavItem(
                route = Profile.route,
                title = "الملف الشخصي",
                icon = Icons.Default.Person
            )
        )
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)
