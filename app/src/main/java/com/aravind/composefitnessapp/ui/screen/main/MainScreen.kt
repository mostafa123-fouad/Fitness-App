package com.aravind.composefitnessapp.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aravind.composefitnessapp.ui.screen.BottomNavScreen
import com.aravind.composefitnessapp.ui.screen.NotificationsScreen
import com.aravind.composefitnessapp.ui.screen.activitytracker.ActivityTrackerScreen
import com.aravind.composefitnessapp.ui.screen.home.HomeScreen
import com.aravind.composefitnessapp.ui.screen.profile.ProfileScreen
import com.aravind.composefitnessapp.ui.screen.progressphoto.ProgressPhotoScreen

@Composable
fun MainScreen(navigateTo: String? = null) {
    val navController = rememberNavController() // Create a new NavHostController
    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.ActivityTracker,
        BottomNavScreen.ProgressPhoto,
        BottomNavScreen.Profile,
        BottomNavScreen.Notification
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(id = if (selected) screen.iconSelected else screen.iconUnSelected),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        },
                        selected = selected,
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = White
                        ),
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Home.route) { HomeScreen() }
            composable(BottomNavScreen.ActivityTracker.route) { ActivityTrackerScreen() }
            composable(BottomNavScreen.ProgressPhoto.route) { ProgressPhotoScreen() }
            composable(BottomNavScreen.Profile.route) { ProfileScreen() }
            composable(BottomNavScreen.Notification.route) { NotificationsScreen() }
        }
    }

    LaunchedEffect(navigateTo) {
        if (navigateTo == "notifications_screen_route") {
            navController.navigate(BottomNavScreen.Notification.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}
