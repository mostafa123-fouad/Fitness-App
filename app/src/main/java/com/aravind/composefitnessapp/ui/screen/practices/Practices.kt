package com.aravind.composefitnessapp.ui.screen.practices

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.screen.activitytracker.Exercise
import com.aravind.composefitnessapp.ui.screen.activitytracker.GifImage
import com.aravind.composefitnessapp.ui.theme.Black
import com.aravind.composefitnessapp.ui.theme.GradientStart
import com.aravind.composefitnessapp.ui.theme.Grey2
import com.aravind.composefitnessapp.ui.theme.RedDanger
import com.aravind.composefitnessapp.ui.theme.poppinsFamily
import kotlinx.coroutines.delay


@Composable
fun TimerScreen(
    exercise: Exercise,
    onBack: () -> Unit,
    initialTime: Int = 60
) {



    var timerValue by remember { mutableStateOf(60) } // قيمة المؤقت بالثواني
    var isRunning by remember { mutableStateOf(false) }
    val timerText = String.format("%02d:%02d", timerValue / 60, timerValue % 60)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x8AAED5EE))
    ) {
        // Header - مشابه لتصميم ActivityTrackerScreen
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Black
                )
            }

            Text(
                text = exercise.name,
                color = Black,
                fontSize = 20.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // عرض الصورة - بنفس أسلوب الكروت السابقة
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            GifImage(
                gifResourceId = exercise.imageRes,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // عرض المؤقت - بنفس أسلوب النصوص في تطبيقك
        Text(
            text = timerText,
            color = Black,
            fontSize = 50.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // أزرار التحكم - بنفس أسلوب الأزرار في تطبيقك
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { isRunning = !isRunning },
                modifier = Modifier
                    .width(120.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRunning) RedDanger else GradientStart
                )
            ) {
                Text(
                    text = if (isRunning) "Pause" else "Start",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Button(
                onClick = {
                    timerValue = 60
                    isRunning = false
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6650a4)
                )
            ) {
                Text(
                    text = "Reset",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold

                )
            }
        }

        // /خلي بالك استخدام اkey1 = isRunningل هو الي حل مشكله عدم الظهور //   إدارة المؤقت
        LaunchedEffect(key1 = isRunning) {
            if (isRunning) {
                while (timerValue > 0) {
                    delay(1000L)
                    timerValue--
                }
                isRunning = false
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TimerScreenPreview() {
    TimerScreen(
        exercise = Exercise(
            name = "Mountain Climber",
            duration = "01:00 MIN",
            imageRes = R.drawable.exersice_1,
            repeats = "Repeat 2 times"
        ),
        onBack = {}
    )
}



