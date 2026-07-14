package com.yallahmasyaf.app.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yallahmasyaf.app.presentation.add_unit.AddUnitScreen
import com.yallahmasyaf.app.presentation.add_unit.AddUnitViewModel
import com.yallahmasyaf.app.presentation.details.DetailsScreen
import com.yallahmasyaf.app.presentation.details.DetailsViewModel
import com.yallahmasyaf.app.presentation.explore.ExploreScreen
import com.yallahmasyaf.app.presentation.explore.ExploreViewModel
import com.yallahmasyaf.app.presentation.favorites.FavoritesScreen
import com.yallahmasyaf.app.presentation.favorites.FavoritesViewModel
import com.yallahmasyaf.app.presentation.navigation.Screen
import com.yallahmasyaf.app.presentation.profile.ProfileScreen
import com.yallahmasyaf.app.presentation.profile.ProfileViewModel
import com.yallahmasyaf.app.presentation.theme.DeepOceanBlue

@Composable
fun MainScreen(
    exploreViewModel: ExploreViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = Screen.bottomNavItems.any { it.route == currentRoute }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    Screen.bottomNavItems.forEach { item ->
                        val isSelected = currentRoute == item.route
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = DeepOceanBlue,
                                indicatorColor = DeepOceanBlue,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Explore.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Explore Screen route
            composable(route = Screen.Explore.route) {
                ExploreScreen(
                    viewModel = exploreViewModel,
                    onUnitClick = { unitId ->
                        navController.navigate(Screen.Details.createRoute(unitId))
                    }
                )
            }
            
            // Favorites Screen route
            composable(route = Screen.Favorites.route) {
                val favoritesViewModel: FavoritesViewModel = hiltViewModel()
                val favoritesState by favoritesViewModel.uiState.collectAsState()
                
                // Refresh list on navigating to this tab
                LaunchedEffect(Unit) {
                    favoritesViewModel.loadFavorites()
                }

                FavoritesScreen(
                    state = favoritesState,
                    onUnitClick = { unitId ->
                        navController.navigate(Screen.Details.createRoute(unitId))
                    },
                    onFavoriteClick = { unitId ->
                        favoritesViewModel.toggleFavorite(unitId)
                        // Trigger ExploreViewModel sync to ensure UI consistency
                        exploreViewModel.loadCoastalUnits()
                    }
                )
            }
            
            // Profile Screen destination
            composable(route = Screen.Profile.route) {
                val profileViewModel: ProfileViewModel = hiltViewModel()
                val profileState by profileViewModel.uiState.collectAsState()

                ProfileScreen(
                    state = profileState,
                    onToggleRoleClick = {
                        profileViewModel.toggleRole()
                    },
                    onAddUnitClick = {
                        navController.navigate(Screen.AddUnit.route)
                    }
                )
            }
            
            // Details Screen destination (opens full screen, hiding bottom bar)
            composable(
                route = Screen.Details.route,
                arguments = listOf(
                    navArgument("unitId") { type = NavType.StringType }
                )
            ) {
                val detailsViewModel: DetailsViewModel = hiltViewModel()
                val detailsState by detailsViewModel.uiState.collectAsState()
                
                DetailsScreen(
                    state = detailsState,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            // Add Unit Wizard destination (opens full screen, hiding bottom bar)
            composable(route = Screen.AddUnit.route) {
                val addUnitViewModel: AddUnitViewModel = hiltViewModel()
                val addUnitState by addUnitViewModel.uiState.collectAsState()

                AddUnitScreen(
                    state = addUnitState,
                    onNextStepClick = {
                        addUnitViewModel.nextStep()
                    },
                    onPreviousStepClick = {
                        addUnitViewModel.previousStep()
                    },
                    onCloseClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
