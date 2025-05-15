package com.aravind.composefitnessapp.ui.screen

import com.aravind.composefitnessapp.R

sealed class BottomNavScreen(var title:String, var iconSelected:Int, var iconUnSelected:Int,var route:String){

    object Home : BottomNavScreen("Home", R.drawable.ic_menu_home_selected, R.drawable.ic_menu_home,"home_screen")
    object ActivityTracker: BottomNavScreen("Activity",R.drawable.ic_menu_activity_selected,R.drawable.ic_menu_activity,"activity_tracker_screen")
    object ProgressPhoto: BottomNavScreen("Photo",R.drawable.ic_menu_photo_selected,R.drawable.ic_menu_photo,"progress_photo_screen")
    object Profile: BottomNavScreen("Profile",R.drawable.ic_menu_profile_selected,R.drawable.ic_menu_profile,"profile_screen")

    object Search :BottomNavScreen("Search",R.drawable.ic_profile_pic ,R.drawable.search1,"search")
    object Notifications: BottomNavScreen("Notifications",R.drawable.ic_icon_notification,R.drawable.ic_icon_notification,"notifications")


    object Timer: BottomNavScreen("Timer",R.drawable.exersice_1,R.drawable.exersice_2,"timer_screen")
    object ToDo : BottomNavScreen("ToDo",R.drawable.ic_button_plus,R.drawable.ic_button_plus,"todo")
    object Compare :BottomNavScreen("compare", R.drawable.ic_button_plus,R.drawable.ic_button_plus,"compare")

    object Tracker :BottomNavScreen("tracker", R.drawable.ic_button_plus,R.drawable.ic_button_plus,"tracker")

    object BMI :BottomNavScreen("BMI", R.drawable.ic_leading_privacy, R.drawable.ic_leading_contact,"bmi")

    object ContactUs :BottomNavScreen("ContactUs", R.drawable.direction, R.drawable.ic_leading_contact,"contactus")
    object Privacy :BottomNavScreen("Privacy", R.drawable.direction, R.drawable.ic_leading_contact,"Privacy")
}
