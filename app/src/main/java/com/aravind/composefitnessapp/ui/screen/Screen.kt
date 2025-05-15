package com.aravind.composefitnessapp.ui.screen

sealed class Screen(val route: String){
    object GetStarted : Screen("getStarted")
    object OnBoarding : Screen("onBoarding")
    object RegisterUser : Screen("registerUser")
    object LoginUser : Screen("loginUser")
    object CompleteProfile : Screen("completeProfile")
    object ChooseGoal : Screen("chooseGoal")
    object Main : Screen("main")
    object Notifications : Screen("notifications")
    object Search : Screen("search")
    object Home : Screen("home")
///////////////
object Timer : Screen("timer_screen")
object ToDo : Screen("todo")
object  Compare :Screen("compare")
object Tracker :Screen("tracker")
object BMI :Screen("bmi")
object ContactUs :Screen("contactus")
object Privacy : Screen("Privacy")
}
