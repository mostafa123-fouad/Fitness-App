@file:OptIn(ExperimentalMaterial3Api::class)

package com.aravind.composefitnessapp.ui.screen.home


import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.screen.Screen
import com.aravind.composefitnessapp.ui.screen.searchbar.SearchScreen
import com.aravind.composefitnessapp.ui.theme.Black
import com.aravind.composefitnessapp.ui.theme.GradientEnd
import com.aravind.composefitnessapp.ui.theme.GradientStart
import com.aravind.composefitnessapp.ui.theme.Grey1
import com.aravind.composefitnessapp.ui.theme.Grey2
import com.aravind.composefitnessapp.ui.theme.Grey4
import com.aravind.composefitnessapp.ui.theme.Pink1
import com.aravind.composefitnessapp.ui.theme.PurpleLight
import com.aravind.composefitnessapp.ui.theme.poppinsFamily
//////////////////////
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.YearMonth
import java.util.Locale
import kotlin.math.pow
import kotlin.math.roundToInt



@Composable
fun HomeScreen(navController: NavController) {

    var showSearchBar by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val sampleItems = listOf(
        "Apple", "Banana", "Cherry", "Date", "Elderberry",
        "Fig", "Grape", "Honeydew", "Kiwi", "Lemon"
    )
    val filteredItems = sampleItems.filter {
        it.contains(query, ignoreCase = true)
    }


    Box(modifier = Modifier.fillMaxSize()) {



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


        ///main content of home
        LazyColumn(
            content = {
                item {
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 25.dp, bottom = 25.dp)
                            .wrapContentSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()

                        ) {
                            Column {
                                Text(
                                    "Welcome Back,",
                                    color = colorResource(R.color.black),
                                    fontSize = 14.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    "Ahmed Tarek",
                                    color = Black,
                                    fontSize = 28.sp,
                                    fontFamily = FontFamily.Cursive,
                                    fontWeight = FontWeight.Bold
                                )

                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp) // مسافة بين الزرين
                            ) {

                                if (showSearchBar) {
                                    SearchScreen(navController = navController)
                                } else {
                                    SearchImage(navController)
                                }

                                NotificationImage(navController)

                            }

                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(205.dp)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bg_home_header),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.matchParentSize(),
                                alignment = Alignment.Center

                            )

                            Column(

                                modifier = Modifier
                                    .height(150.dp)
                                    .padding(start = 18.dp, top = 22.dp, bottom = 12.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    "BMI (Body Mass Index)",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    "You have a normal weight",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.weight(1f)
                                )
                                Button(

                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(start = 0.dp, end = 0.dp, bottom = 3.dp)
                                        .background(
                                            color = Color.White,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .height(30.dp),
                                    onClick = { navController.navigate(route = Screen.BMI.route)  },
                                    contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                                ) {
                                    Text(
                                        "View More", color = Pink1, fontSize = 10.sp,
                                        fontFamily = poppinsFamily, fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }


                            Column(

                                modifier = Modifier
                                    .height(200.dp)
                                    .padding(start = 67.dp, top = 10.dp, bottom = 10.dp, end = 9.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                ResultScreen(weight = 90f, heightCm = 180f)

                            }


                        }
                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = PurpleLight, shape = RoundedCornerShape(12.dp))
                                .padding(15.dp)
                        ) {

                            TodayTargetScreenPreview()
                        }

                        Spacer(modifier = Modifier.height(22.dp))
                        Text(
                            "Activity Status",
                            color = Black,
                            fontSize = 16.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.height(230.dp)
                                .fillMaxWidth()
                                .background(color = PurpleLight, shape = RoundedCornerShape(12.dp))
                        ) {
                            HeartRateChart()

                        }
                        Spacer(modifier = Modifier.height(22.dp))



                        Row(
                            modifier = Modifier.fillMaxSize()

                        ) {
                            Card(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 9.dp
                                ),
                                shape = RoundedCornerShape(15.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(end = 10.dp, start = 8.dp)
                                    .height(430.dp)

                            ) {

                                WaterIntakeScreen()

                            }

                            Card(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                ),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(start = 10.dp)
                            ) {

                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                        .background( Color(0x4691DFF5))
                                ) {

                                    CaloriesCircularCard(
                                        totalAmount = 2f,
                                        intakeAmount = 1.2f,
                                        strokeWidth = 18.dp
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    SleepCircularCard(
                                        totalSleepHours = 8f,
                                        currentSleepHours = 1f,
                                        strokeWidth = 20.dp,
                                        imageRes = R.drawable.sleep
                                    )

                                }
                            }

                        }

                        Spacer(modifier = Modifier.height(22.dp))


                    }
                }
            }, modifier = Modifier
                .fillMaxSize().background(Color.Transparent)
//            .background(
//                brush = Brush.linearGradient(
////                    colors = listOf(Color(0xFF6A1B9A), Color(0xFF225B54)), // التدرج بين لونين
////                    start = Offset(0f, 0f), // البداية من أعلى يسار
////                    end = Offset(1000f, 1000f) // النهاية لأسفل يمين
//
//                    colors = listOf(Color(0xFFB0BEC5), Color(0xFF607D8B).copy(alpha = 0.8f)), // رمادي فاتح إلى رمادي داكن
//                    start = Offset(0f, 0f),
//                    end = Offset(1000f, 1000f)
//
//                )
//
//            )

//            .graphicsLayer {
//                alpha = 0.9f // إضافة تأثير شفاف خفيف
//            }


        )


    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)

}
///////////////////////////////////////////////////////////////////////////////////////////////progress
@Composable
fun VerticalProgress(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val mProgress = animateFloatAsState(targetValue = progress / 100)
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Grey4)
            .width(20.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(if ((1 - mProgress.value) == 0f) 0.0001f else (1 - mProgress.value))
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .weight(mProgress.value)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFC58BF2),
                            Color(0xFF6B50F6),

                        )
                    )
                )
        )
    }

}

@Composable
fun WaterIntakeScreen() {
    val totalLiters = 4000f
    val intakeSteps = listOf(

        "6am - 8am" to 600f,
        "8am - 11am" to 500f,
        "11am - 2pm" to 1000f,
        "2pm - 4pm" to 700f,
        "4pm - now" to 900f,

    )

    var currentStep by remember { mutableStateOf(0) }

    val animatedRatio by animateFloatAsState(
        targetValue = intakeSteps.take(currentStep +1)
            .sumOf { it.second.toDouble() }.toFloat() / totalLiters,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "animatedRatio"
    )

    // Animation trigger
    LaunchedEffect(currentStep) {
        while (currentStep < intakeSteps.lastIndex) {
            delay(1500)
            currentStep++
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF5F5F5)

            ),
        color =Color(0x23D968C6)
    ) {
        Column(

                modifier = Modifier.padding(
                    start = 6.dp,
                    top = 3.dp,
                    end = 1.dp
                )

        ) {
            Text(
                "Water Intake",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                fontFamily = FontFamily.Cursive,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "4 Liters",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7C4DFF)
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Real time updates",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                // Using VerticalProgress component for the progress bar
                VerticalProgress(progress = animatedRatio * 100)

                Spacer(modifier = Modifier.width(20.dp))

                // Time + values
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.height(300.dp)
                ) {
                    intakeSteps.forEachIndexed { index, (time, ml) ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            val dotColor = if (index <= currentStep) Color(0xFF7C4DFF) else Color.Gray
                            Canvas(modifier = Modifier.size(10.dp)) {
                                drawCircle(dotColor)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = time,
                                    color = Color.Black,
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = "${ml.toInt()}ml",
                                    color = if (index <= currentStep) Color(0xFF7C4DFF) else Color.Gray,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun WaterIntakeScreenPreview(){
//    WaterIntakeScreen()
//}

///////////////////////search

@Composable
fun SearchImage(navController: NavController) {
    Box(
        modifier = Modifier
            .size(40.dp) // Adjust size as needed
            .background(Grey2, RoundedCornerShape(12.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.search1),
            contentDescription = "Search",
            modifier = Modifier.size(24.dp)
            .clickable {
                 navController.navigate(route = Screen.Search.route)
                }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SearchImagePreview() {
//    val navController = rememberNavController()
//    SearchImage(navController)
//}
/////////////////////////////////////notification


@Composable
fun NotificationImage(navController: NavController) {
    Box(Modifier.padding(start = 0.dp)

    ) {
        Image(painter = painterResource(id = R.drawable.ic_icon_notification),
            contentDescription = null,
            modifier = Modifier
                .background(color = Grey2, shape = RoundedCornerShape(8.dp))
                .padding(10.dp)
                .clickable {
                    navController.navigate(route = Screen.Notifications.route)
                       }
        )
    }

}
//@Preview(showBackground = true)
//@Composable
//fun NotificationImagePreview(){
//    val navController = rememberNavController()
//    NotificationImage(navController)
//}

/////////////////////////////////////////////////////////CaloriesCircularCard

@Composable
fun CaloriesCircularCard(
    totalAmount: Float, //  ( totalLiters أو totalCalories)
    intakeAmount: Float, //  ( الكمية المستهلكة أو السعرات)
    strokeWidth: Dp // عرض السكتة
) {
    val progress = intakeAmount / totalAmount
    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(
                durationMillis = 6000,
                easing = FastOutSlowInEasing
            )
        )
    }

    Card(
        modifier = Modifier.padding(2.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Calories",
                color = Color.Black,
                fontFamily = FontFamily.Cursive,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Total: ${totalAmount} K Cal",
                color = Color(0xFF90A4AE),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(8.dp))
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()

        ) {

            // رسم الدائرة المتحركة (البرجس)
            CircularCaloriesProgressBarAnimatedLinear(
                //totalAmount = totalAmount,
               // intakeAmount = intakeAmount,
                strokeWidth = strokeWidth,
                animatedProgress = animatedProgress.value,
            )

            // النصوص المتغيرة داخل الدائرة
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val remainingAmount = totalAmount - intakeAmount

                // النص الأول: الكمية المتبقية
                Text(
                    text = String.format("%.1f K Cal", remainingAmount), // عرض الكمية المتبقية
                    color = Color(0xFF607D8B),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                //  النسبة المئوية المستهلكة
                Text(
                    text ="${(animatedProgress.value * 100).toInt()}%" , // عرض النسبة المئوية
                    color = Color(0xFF607D8B),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Composable
fun CircularCaloriesProgressBarAnimatedLinear(
    animatedProgress: Float,
    strokeWidth: Dp
) {
    val gradientBrush = Brush.linearGradient(
      //  colors = listOf(Color.Cyan, Color.Green, Color.Yellow, Color.Red)
        colors = listOf(Color(0xFFE769B1), Color(0xFFF7F8F8),Color(0xFF0DBDAC))
    )

    val backgroundColor = Color(0xFF607D8B)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(170.dp)
    ) {
        Canvas(modifier = Modifier.size(140.dp)) {
            drawArc(
                color = backgroundColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                brush = gradientBrush,
                startAngle = -90f,
                sweepAngle = 360 * animatedProgress,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun CaloriesCircularCardPreview() {
//    CaloriesCircularCard(
//        totalAmount = 5f,  // القيمة الإجمالية (مثلاً 2 لتر ماء)
//        intakeAmount = 4f, // الكمية المستهلكة (مثلاً 1.2 لتر ماء)
//        strokeWidth = 20.dp // عرض السكتة
//    )
//}
//////////////////////////////////////////////////////////////Sleep

@Composable
fun SleepCircularCard(
    totalSleepHours: Float,
    currentSleepHours: Float,
    strokeWidth: Dp = 20.dp,
    imageRes: Int
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(300.dp)
            .height(60.dp)

        ,
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Sleeping",
                color = Color.Black,
                fontFamily = FontFamily.Cursive,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Total: 8 hour",
                color = Color(0xFF90A4AE),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

          //  Spacer(modifier = Modifier.height(1.dp))
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(220.dp)
        ) {
            // الخلفية: صورة الغلاف
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Background Sleep Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(165.dp)
            )

            // البرجرس فوق الصورة
            CircularSleepProgressBar(
                totalSleepHours = totalSleepHours,
                currentSleepHours = currentSleepHours,
                strokeWidth = strokeWidth
            )

            // النصوص في وسط الدايرة
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "LEFT",
                    color = Color.Cyan,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(4.dp))

                val remainingHours = totalSleepHours - currentSleepHours
                val hours = remainingHours.toInt()
                val minutes = ((remainingHours - hours) * 60).toInt()

                Text(
                    text = "$hours h $minutes m",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CircularSleepProgressBar(
    totalSleepHours: Float,
    currentSleepHours: Float,
    strokeWidth: Dp
) {
    val progress = (totalSleepHours - currentSleepHours) / totalSleepHours
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress.coerceIn(0f, 1f),
            animationSpec = tween(
                durationMillis = 5000,
                easing = FastOutSlowInEasing
            )
        )
    }

    val progressColor = Brush.linearGradient(
        colors = listOf(Color(0xFFE769B1), Color(0xFFF7F8F8),Color(0xFF0DBDAC)) // تدرج سماوي
    )

    Canvas(
        modifier = Modifier
            .size(150.dp)
            .padding(10.dp)
    ) {
        // خلفية البرجرس كاملة (رمادي فاتح)
        drawArc(
            color = Color(0xFF7D5260) ,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )

        // البرجرس الفعلي
        drawArc(
            brush = progressColor,
            startAngle = -90f,
            sweepAngle = 360f * animatedProgress.value,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SleepCircularCardPreview() {
//    SleepCircularCard(
//        totalSleepHours = 8f,
//        currentSleepHours = 7f,
//        strokeWidth = 20.dp,
//        imageRes = R.drawable.sleep // ضع صورة مناسبة للنوم هنا
//    )
//}
////////////////////////////////////////////////////////////////// Calender
data class DayModel(
    val dayName: String,
    val dayNumber: Int,
    val isToday: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorizontalCalendar(
    days: List<DayModel>,
    selectedDayIndex: Int,
    onDaySelected: (Int) -> Unit,
    listState: LazyListState
) {
    LazyRow(
        state =  listState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(days) { index, day ->
            val isSelected = index == selectedDayIndex

            Card(
                modifier = Modifier
                    .width(70.dp)
                    .height(65.dp)
                    .clickable { onDaySelected(index) },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) Color(0xFFDF9EF1) else if (day.isToday) Color(0xFFf44336) else Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = day.dayName,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isSelected) Color.White else Color.Gray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = day.dayNumber.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else Color.Black
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayTargetScreen() {

    var selectedDayIndex by remember { mutableStateOf(-1) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val today = LocalDate.now()

    // Function to generate days for the current month
    val days by remember(currentMonth,today) {
        mutableStateOf(generateDaysForMonth(currentMonth, today))
    }

    val listState = rememberLazyListState()

   // selectedDayIndex = days.indexOfFirst { it.dayNumber == today.dayOfMonth }
    LaunchedEffect(days) {
        if (selectedDayIndex == -1) { // فقط إذا كانت القيمة الأولية -1
            selectedDayIndex = days.indexOfFirst { it.dayNumber == today.dayOfMonth }
        }
        if (selectedDayIndex != -1) {
            listState.animateScrollToItem(selectedDayIndex)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Row with arrows and month + year
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "<",
                fontSize = 24.sp,
                modifier = Modifier
                    .clickable {
                        currentMonth = currentMonth.minusMonths(1)
                        selectedDayIndex = 0
                    }, color = Color.Black
            )

            Text(
                text = "${currentMonth.month.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold, color = Color.Black
            )

            Text(
                text = ">",
                fontSize = 24.sp,
                modifier = Modifier
                    .clickable {
                        currentMonth = currentMonth.plusMonths(1)
                        selectedDayIndex = 0
                    }, color = Color.Black
            )
        }

        // LazyRow for the days
        HorizontalCalendar(
            days = days,
            selectedDayIndex = selectedDayIndex,
            onDaySelected = { index ->
                selectedDayIndex = index

            },
                    listState = listState
        )

        // Any other content (like target details) can go here
        LaunchedEffect(selectedDayIndex) {
            if (selectedDayIndex != -1) {
                listState.animateScrollToItem(selectedDayIndex)
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateDaysForMonth(month: YearMonth, today: LocalDate): List<DayModel> {
    return (1..month.lengthOfMonth()).map { dayOfMonth ->
        val date = month.atDay(dayOfMonth)
        DayModel(
            dayName = date.dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault()), // Mon, Tue, ...
            dayNumber = dayOfMonth,
            isToday = date == today // Mark today
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TodayTargetScreenPreview() {
    MaterialTheme {
        TodayTargetScreen()
    }
}
/////////////////////////////////////////////////////////////////////////////// BMI
@Composable
fun ResultScreen(weight: Float, heightCm: Float) {
    val heightM = heightCm / 100
    val bmi = weight / (heightM * heightM)

    val bmiCategory = when {
        bmi < 18.5 -> "Underweight"
        bmi < 25 -> "Normal weight"
        bmi < 30 -> "Overweight"
        else -> "Obese"
    }

    val targetAngle = (bmi / 40f) * 360f // فرضًا الـ Max BMI هو 40

    // تدرج لوني سلس
    val gradientColors = listOf(
        Color(0xFF64B5F6), // أزرق فاتح
        Color(0xFF81C784), // أخضر فاتح
        Color(0xFFFFEB3B), // أصفر
        Color(0xFFFF7043)  // أحمر برتقالي
    )


    val animatedAngle = remember { Animatable(0f) }
    LaunchedEffect(targetAngle) {
        animatedAngle.animateTo(
            targetAngle,
            animationSpec = tween(durationMillis = 2500, easing = FastOutSlowInEasing)
        )
    }

    Column(
    //    modifier = Modifier
//            .padding(14.dp),
 //     verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.End
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(150.dp)
        ) {
            Canvas(modifier = Modifier.size(138.dp)) {
                // الخلفية الرمادية
                drawArc(
                    color = Color.LightGray,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = true
                )

                // قوس ملون مع تدرج لوني سلس
                drawArc(
                    brush = Brush.linearGradient(
                        colors = gradientColors, // تدرج ألوان سلس
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height)
                    ),
                    startAngle = -90f,
                    sweepAngle =animatedAngle.value,
                    useCenter = true
                )
            }

            Text(
                text = String.format("%.1f", bmi),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween


            ){
        Text(
            text = "You have a $bmiCategory",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.White

        )
    }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun ResultScreenPreview() {
//    ResultScreen(weight = 90f, heightCm = 180f)
//}
/////////////////////////////////////////////////////////////////////// Heart Rate
@Composable
fun HeartRateChart() {
    val dataPoints = listOf(60f, 65f, 70f, 68f, 72f, 90f, 78f, 76f, 74f, 77f)
    val maxValue = dataPoints.maxOrNull() ?: 100f
    val minValue = dataPoints.minOrNull() ?: 0f

    val showDetails = remember { mutableStateOf(false) }
    val selectedPointIndex = remember { mutableStateOf(-1) }  // لتخزين النقطة المختارة

    val gradientColors = listOf(Color(0xFF00BCD4), Color(0xFF2196F3))
    val timeLabels = listOf(
        "45 mins ago", "40 mins ago", "35 mins ago", "30 mins ago", "25 mins ago",
        "20 mins ago", "15 mins ago", "10 mins ago", "5 mins ago", "Now"
    )

    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFE0F7FA), Color(0xFFB3E5FC))
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {

        if (showDetails.value) {
            Text("Heart Rate", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            Text("${dataPoints[selectedPointIndex.value]} BPM", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val spacing = remember { mutableStateOf(0f) }
            val canvasPoints = remember { mutableStateListOf<Offset>() }

            Canvas(modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    spacing.value = it.size.width.toFloat() / (dataPoints.size - 1)
                }
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        // العثور على أقرب نقطة للضغط عليها
                        val tappedIndex = canvasPoints.indexOfFirst { it.distance(offset) < 20f }
                        if (tappedIndex != -1) {
                            selectedPointIndex.value = tappedIndex
                            showDetails.value = true  // عرض النصوص عند الضغط على النقطة
                        }
                    }
                }


            ) {
                val chartHeight = size.height
                val points = dataPoints.mapIndexed { index, value ->
                    val x = spacing.value * index
                    val y = chartHeight - ((value - minValue) / (maxValue - minValue)) * chartHeight
                    Offset(x, y)
                }
                canvasPoints.clear()
                canvasPoints.addAll(points)

                // خط المنحنى
                val path = Path().apply {
                    moveTo(points.first().x, points.first().y)
                    for (i in 1 until points.size) {
                        lineTo(points[i].x, points[i].y)
                    }
                }

                drawPath(
                    path = path,
                    brush = Brush.linearGradient(gradientColors),
                    style = Stroke(width = 4f, cap = StrokeCap.Round)
                )

                // تعبئة تحت المنحنى
                val fillPath = Path().apply {
                    addPath(path)
                    lineTo(points.last().x, size.height)
                    lineTo(points.first().x, size.height)
                    close()
                }

                drawPath(
                    path = fillPath,
                    brush = Brush.verticalGradient(
                        colors = gradientColors.map { it.copy(alpha = 0.2f) }
                    )
                )



// رسم خطي بداية ونهاية المعدل الطبيعي (60-100 BPM)
                val normalMinY = chartHeight - ((60f - minValue) / (maxValue - minValue)) * chartHeight
                val normalMaxY = chartHeight - ((100f - minValue) / (maxValue - minValue)) * chartHeight
                drawLine(
                    start = Offset(0f, normalMinY),
                    end = Offset(size.width, normalMinY),
                    color = Color(0xFFFF9E80),  // اللون الأخضر عند 60 BPM
                    strokeWidth = 2f
                )
                drawLine(
                    start = Offset(0f, normalMaxY),
                    end = Offset(size.width, normalMaxY),
                    color = Color(0xFF4CAF50),  // اللون الأخضر عند 100 BPM
                    strokeWidth = 2f
                )

                // إضافة نص توضيحي عند الخطوط
                drawContext.canvas.nativeCanvas.apply {
                    drawText("Normal Range (60-100 BPM)", 30f, normalMinY - 10f, Paint().apply {
                        color = android.graphics.Color.parseColor("#4CAF50")
                        textSize = 27f
                    })
                }

















                // نقاط كل نقطة
                points.forEachIndexed { index, point ->
                    drawCircle(Color.White, radius = 12f, center = point, style = Stroke(width = 3f))
                    drawCircle(Color(0xFFCE93D8), radius = 6f, center = point)
                }
            }

            // تولتيب لكل نقطة
            canvasPoints.forEachIndexed { index, offset ->
                if (showDetails.value && selectedPointIndex.value != -1) {
                    val offset = canvasPoints[selectedPointIndex.value]


                    Column(
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    x = (offset.x - 60f).toInt(),
                                    y = (offset.y - 100f).toInt()
                                )
                            }
                            .background(Color(0xFFCE93D8), shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text =timeLabels.getOrElse(selectedPointIndex.value) { "${selectedPointIndex.value * 5} mins ago" } ,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                        Text(
                            text ="${dataPoints[selectedPointIndex.value]} BPM" ,
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// دالة لحساب المسافة بين نقطتين
fun Offset.distance(to: Offset): Float {
    return Math.sqrt(((this.x - to.x).pow(2) + (this.y - to.y).pow(2)).toDouble()).toFloat()
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun HeartRateChartPreview() {
//    HeartRateChart()
//}
