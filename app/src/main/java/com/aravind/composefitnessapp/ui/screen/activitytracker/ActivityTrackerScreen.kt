package com.aravind.composefitnessapp.ui.screen.activitytracker

import android.annotation.SuppressLint
import android.os.Build
import android.os.CountDownTimer
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.screen.Screen
import com.aravind.composefitnessapp.ui.theme.Black
import com.aravind.composefitnessapp.ui.theme.GradientEnd
import com.aravind.composefitnessapp.ui.theme.GradientStart
import com.aravind.composefitnessapp.ui.theme.Grey1
import com.aravind.composefitnessapp.ui.theme.Grey2
import com.aravind.composefitnessapp.ui.theme.Grey3
import com.aravind.composefitnessapp.ui.theme.PinkLight
import com.aravind.composefitnessapp.ui.theme.PurpleLight
import com.aravind.composefitnessapp.ui.theme.RedDanger
import com.aravind.composefitnessapp.ui.theme.poppinsFamily
import kotlinx.android.parcel.Parcelize

@Composable
fun ActivityTrackerScreen(navController: NavController) {

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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            item {
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 25.dp, bottom = 25.dp)
                        .wrapContentHeight()
                        .fillMaxWidth()

                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Activity Tracker",
                            color = Black,
                            fontSize = 20.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(alignment = Alignment.Center)
                        )

                        Image(painter = painterResource(id = R.drawable.ic_options_menu),
                            contentDescription = null,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterEnd)
                                .background(color = Grey2, shape = RoundedCornerShape(8.dp))
                                .padding(start = 13.dp, end = 13.dp, top = 16.dp, bottom = 16.dp)
                                .clickable { println("Button Clicked!") })
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = PurpleLight, shape = RoundedCornerShape(12.dp))
                            .padding(start = 18.dp, end = 18.dp, top = 15.dp, bottom = 15.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Today Target",
                                color = Black,
                                fontSize = 14.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_button_plus),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(41.dp)
                                    .clickable {
                                        navController.navigate(Screen.ToDo.route)
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Row(
                                modifier = Modifier
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(
                                        start = 12.dp,
                                        top = 8.dp,
                                        end = 12.dp,
                                        bottom = 8.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_glass),
                                    contentDescription = null,
                                    Modifier.size(45.dp)
                                )
                                Column(

                                    modifier = Modifier.padding(start = 5.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        "8L",
                                        fontSize = 14.sp,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.Medium,
                                        style = TextStyle(
                                            brush = Brush.horizontalGradient(
                                                listOf(
                                                    GradientStart,
                                                    GradientEnd
                                                )
                                            )
                                        )
                                    )
                                    Text(
                                        "Water Intake",
                                        color = Grey1,
                                        fontSize = 12.sp,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.Normal,
                                        maxLines = 1
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(
                                        start = 16.dp,
                                        top = 8.dp,
                                        end = 12.dp,
                                        bottom = 8.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_steps_boots),
                                    contentDescription = null,
                                    Modifier.size(45.dp)
                                )
                                Column(

                                    modifier = Modifier.padding(start = 5.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        "2400",
                                        fontSize = 14.sp,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.Medium,
                                        style = TextStyle(
                                            brush = Brush.horizontalGradient(
                                                listOf(
                                                    GradientStart,
                                                    GradientEnd
                                                )
                                            )
                                        )
                                    )
                                    Text(
                                        "Foot Steps",
                                        color = Grey1,
                                        fontSize = 12.sp,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.Normal,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Latest Activity",
                            color = Black,
                            fontSize = 16.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "See more",
                            color = Grey1,
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    val cardShape = RoundedCornerShape(16.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(


                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .shadow(
                                shape = cardShape,
                                spotColor = Grey3,
                                elevation = 20.dp
                            ),
                        shape = cardShape
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier

                                .padding(18.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_profile_pic),
                                contentDescription = null,
                                modifier = Modifier.size(55.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    "Drinking 300ml Water",
                                    color = Black,
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    "About 3 minutes ago",
                                    color = Grey1,
                                    fontSize = 10.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 1
                                )
                            }
                            Icon(
                                imageVector = Icons.Rounded.MoreVert,
                                contentDescription = "Icon",
                                tint = Grey1,
                                modifier = Modifier.align(alignment = Alignment.Top)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(


                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .shadow(
                                shape = cardShape,
                                spotColor = Grey3,
                                elevation = 20.dp
                            ),
                        shape = cardShape
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier

                                .padding(18.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_profile_pic),
                                contentDescription = null,
                                modifier = Modifier.size(55.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    "Eat Healthy Snack",
                                    color = Black,
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    "About 10 minutes ago",
                                    color = Grey1,
                                    fontSize = 10.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 1
                                )
                            }
                            Icon(
                                imageVector = Icons.Rounded.MoreVert,
                                contentDescription = "Icon",
                                tint = Grey1,
                                modifier = Modifier.align(alignment = Alignment.Top)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(

                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .shadow(
                                shape = cardShape,
                                spotColor = Grey3,
                                elevation = 20.dp
                            ),
                        shape = cardShape
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier

                                .padding(18.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_weight_lift),
                                contentDescription = null,
                                modifier = Modifier.size(55.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    "Fullbody Workout",
                                    color = Black,
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    "Today, 10:00 am",
                                    color = Grey1,
                                    fontSize = 10.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 1
                                )
                            }
                            Icon(
                                imageVector = Icons.Rounded.MoreVert,
                                contentDescription = "Icon",
                                tint = Grey1,
                                modifier = Modifier.align(alignment = Alignment.Top)
                            )
                        }
                    }


                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(start = 17.dp, end = 15.dp)
                ) {
                    Text(
                        "ٍStart Activity",
                        color = Black,
                        fontSize = 16.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "See more",
                        color = Grey1,
                        fontSize = 12.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Medium
                    )
                }

                WorkoutListScreen(navController= navController)


            }

        }

    )
}
    }

@Composable
@Preview
fun ActivityTrackerScreenPreview() {
    ActivityTrackerScreen(navController = rememberNavController())
}

////////////////////////////////////////////////////////////////gif
@SuppressLint("ObsoleteSdkInt")
@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    gifResourceId: Int, // استخدم هذا المتغير لتحديد الصورة
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory()) // للـ SDK >= 28
            } else {
                add(GifDecoder.Factory()) // للـ SDK < 28
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                .data(gifResourceId) // اسم الصورة هنا
                .build(),
            imageLoader = imageLoader
        ),
        contentDescription = "GIF Image",
        modifier = modifier.fillMaxWidth(), // التحكم في حجم الصورة
    )
}
//////////////////////////////////////////////////////// card practise
@Composable
fun WorkoutListScreen(navController: NavController) {
    val exercises = listOf(
        Exercise("Mountain Climber", "Repeat 2 times", "01:00 MIN", R.drawable.exersice_1),
        Exercise("Basic Crunches", "Repeat 2 times", "01:00 MIN", R.drawable.exersice_3),
        Exercise("Bench Dips", "Repeat 2 times", "01:00 MIN", R.drawable.exersice_10),
        Exercise("Bicycle Crunches", "Repeat 2 times", "01:00 MIN", R.drawable.exersice_4),
        Exercise("Leg Raise", "Repeat 2 times", "01:00 MIN", R.drawable.exersice_5),
        Exercise("Alternative Heel Touch", "Repeat 2 times", "01:00 MIN", R.drawable.exersice_6),
        Exercise("Leg Up Crunches","Repeat 2 times", "01:00 MIN", R.drawable.exersice_7),
        Exercise("Sit Up","Repeat 2 times", "01:00 MIN", R.drawable.exersice_8),
        Exercise("Alternative V Ups","Repeat 2 times","01:00 MIN", R.drawable.exersice_9),
        Exercise("Plank Rotation","Repeat 2 times","01:00 MIN", R.drawable.exersice_2),
        Exercise("Plank with Leg Left","Repeat 2 times","01:00 MIN", R.drawable.exersice_11),
        Exercise("Russian Twist","Repeat 2 times","01:00 MIN", R.drawable.exersice_12),
        Exercise("Bridge","Repeat 2 times","01:00 MIN", R.drawable.exersice_13),
        Exercise("Vertica Leg Crunches","Repeat 2 times","01:00 MIN", R.drawable.exersice_14),
        Exercise("Wind Mill","Repeat 2 times","01:00 MIN", R.drawable.exersice_15),

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(19.dp)

    ) {
        exercises.forEach { exercise ->

            ExerciseCard(exercise = exercise, onClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set("exercise", exercise)
                navController.navigate(Screen.Timer.route)
            })

            Spacer(modifier = Modifier.height(12.dp))
        }

    }

}

@Composable
fun ExerciseCard(exercise: Exercise,onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                    onClick ()

             }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(14.dp)
        ) {
            GifImage(
                gifResourceId = exercise.imageRes, // استخدام دالة GifImage لعرض الصورة المتحركة
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold, color = Color.Black
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Refresh, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = exercise.repeats, style = MaterialTheme.typography.bodySmall, color = Color.Black)

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = exercise.duration, style = MaterialTheme.typography.bodySmall, color = Color.Black)
                }
            }





        }
    }
}

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Exercise(
    val name: String,
    val repeats: String,
    val duration: String,
    val imageRes: Int
) : Parcelable

//@Preview(showBackground = true, showSystemUi = false)
//@Composable
//fun WorkoutListScreenPreview() {
//    MaterialTheme {
//        WorkoutListScreen()
//    }
//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExerciseCardPreview() {
    MaterialTheme {
        ExerciseCard(
            Exercise(
                name = "Mountain Climber",
                repeats = "Repeat 2 times",
                duration = "01:00 MIN",
                imageRes = R.drawable.exersice_2 // يمكنك استبدال هذا بمصدر صورة متحركة

            ),
          onClick={}
        )

    }
}


