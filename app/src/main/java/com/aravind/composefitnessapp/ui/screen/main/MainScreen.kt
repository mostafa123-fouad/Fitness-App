package com.aravind.composefitnessapp.ui.screen.main

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.screen.BottomNavScreen
import com.aravind.composefitnessapp.ui.screen.Screen
import com.aravind.composefitnessapp.ui.screen.activitytracker.ActivityTrackerScreen
import com.aravind.composefitnessapp.ui.screen.activitytracker.Exercise
import com.aravind.composefitnessapp.ui.screen.home.BMIPage
import com.aravind.composefitnessapp.ui.screen.home.HomeScreen
import com.aravind.composefitnessapp.ui.screen.notfication.NotificationScreen
import com.aravind.composefitnessapp.ui.screen.practices.TimerScreen
import com.aravind.composefitnessapp.ui.screen.profile.ContactUsScreen
import com.aravind.composefitnessapp.ui.screen.profile.PrivacyPolicyScreen
import com.aravind.composefitnessapp.ui.screen.profile.ProfileScreen
import com.aravind.composefitnessapp.ui.screen.progressphoto.AlbumViewState
import com.aravind.composefitnessapp.ui.screen.progressphoto.ComparePhotoScreen
import com.aravind.composefitnessapp.ui.screen.progressphoto.MonthlyProgressScreen
import com.aravind.composefitnessapp.ui.screen.progressphoto.ProgressPhotoScreen
import com.aravind.composefitnessapp.ui.screen.searchbar.SearchScreen
import com.aravind.composefitnessapp.ui.screen.todo.ToDoApp
import com.aravind.composefitnessapp.ui.theme.NoRippleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(   ) {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.ActivityTracker,
        BottomNavScreen.ProgressPhoto,
        BottomNavScreen.Profile,

    )

    var forceShowBar by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val bottomBarVisibleState = rememberScrollDirectionState(listState)
    val bottomBarVisible by remember {
        derivedStateOf {
            bottomBarVisibleState.value || forceShowBar
        }
    }
    // State to track the visibility of the bottom navigation bar
    var showArrow by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color.Transparent,

        bottomBar = {

            if (bottomBarVisible) {

                CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

                    NavigationBar(

                        modifier = Modifier.wrapContentSize()
                            .wrapContentSize()
                            .clip(RoundedCornerShape(38.dp)) // شكل المدورة للحواف
                            .border(
                                3.dp,
                                colorResource(id = R.color.white),
                                RoundedCornerShape(50.dp)
                            ),

                        containerColor = colorResource(R.color.black).copy(alpha = 0.5f),
                        tonalElevation = 0.dp,

                        content = {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            items.forEach { screen ->
                                val selected =
                                    currentDestination?.hierarchy?.any { it.route == screen.route } == true

                                NavigationBarItem(

                                    icon = {

                                        Icon(
                                            painterResource(id = if (selected) screen.iconSelected else screen.iconUnSelected),
                                            contentDescription = "",
                                            tint = Color.Unspecified,
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)


                                        )
                                    },
//                            colors = NavigationBarItemDefaults.colors(
//                                indicatorColor = colorResource(R.color.white).copy(alpha = 0.6f) //////دي بتاع الدائره السوداء
//                            ),
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }

                                    },

                                )
                            }
                        })
                }
            }else {
                // Show arrow when the bottom bar is hidden
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomCenter

                ) {
                    IconButton(onClick = { forceShowBar = true

                      // Auto hide again
                        coroutineScope.launch {
                            delay(4000)
                            forceShowBar = false
                        }

                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Show Bottom Bar",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }


        },
////////////////////////////////////support
        floatingActionButton = {
            Box(
                modifier = Modifier.offset(y = (0).dp ,x= 25.dp)
                    .fillMaxSize()
                    .padding(bottom = 210.dp, end = 0.dp,), // موقعه فوق BottomBar وباليمين
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    colorResource(R.color.purple_200).copy(alpha = 0.3f),
                                    colorResource(R.color.white).copy(alpha = 0.3f)
                                )
                            ),
                            shape = CircleShape
                        )
                        .padding(5.dp)
                ) {
                    FloatingActionButton(
                        onClick = { /* TODO */ },
                        containerColor = Color.Transparent, // شفاف تمامًا
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape),
                        contentColor = Color.White,
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                        content = {
                            Image(
                                painter = painterResource(R.drawable.secices2),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                                    .alpha(0.9f) // الأيقونة نفسها شفافة شوية
                            )
                        }
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center, // مجرد توجيه منطقي
        contentColor = Color.Transparent

//        floatingActionButton = {
//            Box(
//                modifier = Modifier
//                    .size(60.dp)
//                    .offset(y = (52).dp)
//                    .clip(CircleShape)
//                    .background(
//                        brush = Brush.linearGradient(
//                            colors = listOf(
//                                colorResource(R.color.purple_200),
//                                colorResource(R.color.white)
//                            )
//                        ),
//                        shape = CircleShape
//                    )
//                    .padding(7.dp)
//            ) {
//                FloatingActionButton(
//                    onClick = {},
//                   containerColor = colorResource(id = R.color.white),
//                    modifier = Modifier
//                        .size(58.dp)
//                        .clip(CircleShape),
//                    contentColor = Color.Red,
//                    content = {
//                        Image(
//                            painter = painterResource(R.drawable.secices2), contentDescription = null
//                            , modifier = Modifier.size(55.dp)
//
//                        )
//                    }
//                )
//            }
//        },
//floatingActionButtonPosition = FabPosition.Center,
//contentColor = colorResource(R.color.purple_700),


///////////////////////////////////////
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {


            // ✅ الخلفية: صورة تملأ الشاشة
            Image(
                painter = painterResource(id = R.drawable.background1), // غيّرها حسب اسم الصورة عندك
                contentDescription = null,
                contentScale = ContentScale.Crop, // تغطي الشاشة
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = 0.9f // شفافية بسيطة
                    }
            )

            NavHost(
                navController,
                startDestination = BottomNavScreen.Home.route,
                Modifier.padding(innerPadding)

            ) {
                composable(BottomNavScreen.Home.route) { HomeScreen(navController) }

                composable(BottomNavScreen.Search.route) { SearchScreen(navController) }
                composable(BottomNavScreen.Notifications.route) { NotificationScreen(navController) }

                composable(BottomNavScreen.ActivityTracker.route) {
                    ActivityTrackerScreen(
                        navController
                    )
                }
                composable(BottomNavScreen.ProgressPhoto.route) { ProgressPhotoScreen(navController) }
                composable(BottomNavScreen.Profile.route) { ProfileScreen(navController) }


                composable(BottomNavScreen.Timer.route) {
                    val exercise = navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<Exercise>("exercise")

                    exercise?.let {
                        TimerScreen(
                            exercise = it,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }

                composable(BottomNavScreen.ToDo.route) {
                    ToDoApp(navController, onBack = { navController.popBackStack() })
                }

                /////////////////////////////////////
                composable(BottomNavScreen.Compare.route) {
                    ComparePhotoScreen(navController, onBack = { navController.popBackStack() })
                }

                composable(BottomNavScreen.Tracker.route) {
                    val dummyState = AlbumViewState(
                        monthlyPhotos = List(8) { index ->
                            if (index % 2 == 0) null else ImageBitmap(100, 100)
                        }
                    )

                    MonthlyProgressScreen(
                        viewState = dummyState,
                        onTakePhotoClick = { /* navigate to camera */ },
                        onPickPhotoClick = { /* navigate to gallery */ },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(BottomNavScreen.BMI.route){
                    BMIPage(navController = navController,
                        onBackClick={navController.popBackStack()})
                }

                composable(BottomNavScreen.ContactUs.route){
                    ContactUsScreen(onBack={navController.popBackStack()})
                }

                composable(BottomNavScreen.Privacy.route){
                    PrivacyPolicyScreen(onBack={navController.popBackStack()})
                }
            }
        }
    }

}


@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}


////////////////////////////////////////////////////////

@Composable
fun rememberScrollDirectionState(
    listState: LazyListState,
    delayMillis: Long = 4000
): State<Boolean> {
    var visible by remember { mutableStateOf(true) }
    var lastOffset by remember { mutableStateOf(0) }
    var forceShow by remember { mutableStateOf(false) }

    // scroll direction
    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        val currentOffset = listState.firstVisibleItemScrollOffset
        if (currentOffset < lastOffset) {
            visible = true
            forceShow = false
        } else if (currentOffset > lastOffset) {
            if (!forceShow) visible = false
        }
        lastOffset = currentOffset
    }

    // hide after delay
    LaunchedEffect(visible) {
        if (visible && !forceShow) {
            delay(delayMillis)
            visible = false
        }
    }

    // Combine visible + forceShow into one observable State
    return rememberUpdatedState(visible || forceShow)
}