package com.aravind.composefitnessapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aravind.composefitnessapp.ui.screen.Screen
import com.aravind.composefitnessapp.ui.screen.choosegoal.ChooseGoalScreen
import com.aravind.composefitnessapp.ui.screen.completeprofile.CompleteProfileScreen
import com.aravind.composefitnessapp.ui.screen.contactus.ContactUsScreen
import com.aravind.composefitnessapp.ui.screen.getstarted.GetStartedScreen
import com.aravind.composefitnessapp.ui.screen.main.MainScreen
import com.aravind.composefitnessapp.ui.screen.onboarding.OnBoardingScreen
import com.aravind.composefitnessapp.ui.screen.privacypolicy.PrivacyPolicyScreen
import com.aravind.composefitnessapp.ui.screen.registeruser.RegisterUserScreen
import com.aravind.composefitnessapp.ui.theme.ComposeFitnessAppTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    private var isDialogShown = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createChannel()
        FirebaseApp.initializeApp(this)
        setContent {
            val navController = rememberNavController()
            val navigateTo = intent.getStringExtra("navigateTo")
            ComposeFitnessAppTheme {
                NavHost(navController = navController, startDestination = Screen.GetStarted.route) {
                    composable(Screen.GetStarted.route) {
                        GetStartedScreen(onGetStarted = { navController.navigate(Screen.OnBoarding.route) })
                    }
                    composable(Screen.OnBoarding.route) {
                        OnBoardingScreen(onBoardingFinished = { navController.navigate(Screen.RegisterUser.route) })
                    }
                    composable(Screen.RegisterUser.route) {
                        RegisterUserScreen(onRegisterSuccess = { navController.navigate(Screen.CompleteProfile.route) })
                    }
                    composable(Screen.CompleteProfile.route) {
                        CompleteProfileScreen(onCompleteProfile = { navController.navigate(Screen.ChooseGoal.route) })
                    }
                    composable(Screen.ChooseGoal.route) {
                        ChooseGoalScreen(onGoalConfirmed = { navController.navigate(Screen.Main.route) })
                    }
                    composable(Screen.Main.route) {
                        MainScreen(navigateTo = navigateTo, mainNavController = navController)
                    }


                }
            }
        }
    }
    fun handlePermission(): ActivityResultLauncher<String> {
        val handler=registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted->
            if(isGranted){
                // show notification
                showNotification()
            }
            else{
                // show alert dialog "cancel", "allow"
                isDialogShown.value=true
            }
        }
        return handler
    }

    fun createChannel(){
        val channel= NotificationChannel("1","Main Channel", NotificationManager.IMPORTANCE_HIGH)
        channel.description="Some Channel description ..."
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission")
    fun showNotification(){

        val builder= NotificationCompat.Builder(this,"1")
        val i = Intent(this, MainActivity::class.java).apply {
            putExtra("navigateTo", "notifications_screen_route")
        }
        val pendingIntent= PendingIntent.getActivity(this,200,i, PendingIntent.FLAG_IMMUTABLE)
        builder
            .setSmallIcon(R.drawable.ic_fitness)
            .setContentTitle("Fitness Notification")
            .setContentText("You activated the notification successfully!")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        NotificationManagerCompat.from(this).notify(500,builder.build())

    }
    @Composable
    fun PermissionDeniedDialog(onTextButtonClick:()->Unit){
        AlertDialog  (
            onDismissRequest = {},
            confirmButton= {
                TextButton({onTextButtonClick()}) {
                    Text("Allow")
                }
            },
            dismissButton={
                TextButton({onTextButtonClick()}) {
                    Text("Cancel")
                }
            },
            title={ Text("Permission Denied!") },
            text= {
                Text(
                    "You didn't \"allow\" the permission, so the notification will be canceled"
                )
            })
    }

}

