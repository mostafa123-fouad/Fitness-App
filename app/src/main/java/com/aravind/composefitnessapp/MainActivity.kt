package com.aravind.composefitnessapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aravind.composefitnessapp.ui.screen.BottomNavScreen
import com.aravind.composefitnessapp.ui.screen.Screen
import com.aravind.composefitnessapp.ui.screen.activitytracker.ActivityTrackerScreen
import com.aravind.composefitnessapp.ui.screen.activitytracker.Exercise
import com.aravind.composefitnessapp.ui.screen.choosegoal.ChooseGoalScreen
import com.aravind.composefitnessapp.ui.screen.completeprofile.CompleteProfileScreen
import com.aravind.composefitnessapp.ui.screen.getstarted.GetStartedScreen
import com.aravind.composefitnessapp.ui.screen.home.BMIPage
import com.aravind.composefitnessapp.ui.screen.home.HomeScreen
import com.aravind.composefitnessapp.ui.screen.loginuser.LoginScreen
import com.aravind.composefitnessapp.ui.screen.main.MainScreen
import com.aravind.composefitnessapp.ui.screen.notfication.NotificationScreen
import com.aravind.composefitnessapp.ui.screen.onboarding.OnBoardingScreen
import com.aravind.composefitnessapp.ui.screen.practices.TimerScreen
import com.aravind.composefitnessapp.ui.screen.profile.ContactUsScreen
import com.aravind.composefitnessapp.ui.screen.profile.PrivacyPolicyScreen
import com.aravind.composefitnessapp.ui.screen.progressphoto.AlbumViewState
import com.aravind.composefitnessapp.ui.screen.progressphoto.ComparePhotoScreen
import com.aravind.composefitnessapp.ui.screen.progressphoto.MonthlyProgressScreen
import com.aravind.composefitnessapp.ui.screen.progressphoto.ProgressPhotoScreen
import com.aravind.composefitnessapp.ui.screen.registeruser.RegisterUserScreen
import com.aravind.composefitnessapp.ui.screen.searchbar.SearchScreen
import com.aravind.composefitnessapp.ui.screen.todo.ToDoApp
import com.aravind.composefitnessapp.ui.theme.ComposeFitnessAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeFitnessAppTheme {
                NavHost(navController = navController, startDestination = Screen.GetStarted.route) {


                    composable(Screen.GetStarted.route) {
                        GetStartedScreen(onGetStarted = { navController.navigate(Screen.OnBoarding.route) })
                    }
                    composable(Screen.OnBoarding.route) {
                        OnBoardingScreen(onBoardingFinished = { navController.navigate(Screen.RegisterUser.route) })
                    }

                    composable(Screen.RegisterUser.route) {
                        RegisterUserScreen(onRegisterSuccess = { navController.navigate(Screen.CompleteProfile.route) },
                            onNavigateToLogin = { navController.navigate(Screen.LoginUser.route) }
                            )

                    }

                    composable(Screen.LoginUser.route){
                       LoginScreen(onLoginSuccess = { navController.navigate(Screen.Main.route) },
                           onNavigateToRegister = { navController.navigate(Screen.RegisterUser.route) } // إضافة دالة للانتقال إلى Register
                           )
                    }

                    composable(Screen.CompleteProfile.route) {
                        CompleteProfileScreen(onCompleteProfile = { navController.navigate(Screen.ChooseGoal.route) })
                    }
                    composable(Screen.ChooseGoal.route) {
                        ChooseGoalScreen(onGoalConfirmed = { navController.navigate(Screen.Main.route) })
                    }

                    composable(Screen.Main.route) {
                        MainScreen()
                    }

                    composable(Screen.Home.route) {
                      HomeScreen(navController=navController)
                    }
                    composable(Screen.Search.route) {
                        SearchScreen(navController)
                    }

                    composable(Screen.Notifications.route) {
                        NotificationScreen(navController)

                    }

                    /////////////////////
                    composable(Screen.Timer.route) { backStackEntry ->
                        val exercise = backStackEntry.savedStateHandle.get<Exercise>("exercise")
                        if (exercise != null) {
                            TimerScreen(
                                exercise = exercise,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }

                   composable(Screen.ToDo.route){
                      ToDoApp(
                          navController,
                          onBack = { navController.popBackStack() }
                      )
                   }



                 composable(Screen.Compare.route){
                     ComparePhotoScreen(navController,onBack = { navController.popBackStack()} )
                 }


                    composable(Screen.Tracker.route) {

                        val dummyState = AlbumViewState(
                            monthlyPhotos = List(8) { index ->
                                if (index % 2 == 0) null else ImageBitmap(100, 100)
                            }
                        )

                        MonthlyProgressScreen(
                            viewState = dummyState,
                            onTakePhotoClick = { monthIndex ->
                                // navigate to camera screen, example:
                                // navController.navigate("camera_screen/$monthIndex")
                            },
                            onPickPhotoClick = { monthIndex ->
                                // navigate to gallery screen, example:
                                // navController.navigate("gallery_screen/$monthIndex")
                            },
                            onBack = {
                                navController.popBackStack() // يرجع للشاشة السابقة
                            }
                        )
                    }


                     composable(Screen.BMI.route){
                            BMIPage(navController = navController,onBackClick={navController.popBackStack()})
                     }


                    composable(Screen.ContactUs.route){
                        ContactUsScreen(onBack={navController.popBackStack()})

                    }
                    composable(Screen.Privacy.route){
                        PrivacyPolicyScreen(onBack={navController.popBackStack()})
                    }



                }


            }
        }



    }
}
