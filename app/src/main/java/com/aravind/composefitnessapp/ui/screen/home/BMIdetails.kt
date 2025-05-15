package com.aravind.composefitnessapp.ui.screen.home



import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.theme.Black

@Composable
fun BMIPage( navController: NavController,onBackClick: () -> Unit) {

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Back Button
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 16.dp),

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back", tint = Black
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = "BMI - Body Mass Index",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = """
                Body Mass Index (BMI) is a measure used to evaluate a person's weight relative to their height. 
                It is calculated by dividing a person's weight in kilograms by the square of their height in meters. 
                BMI is used to determine whether a person is underweight, has a normal weight, is overweight, or obese.

                How to calculate BMI:
                BMI = Weight (kg) / Height (m)²
            """.trimIndent(),
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Classification Title
            Text(
                text = "Classifications:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // BMI Categories
            Column(modifier = Modifier.padding(start = 16.dp))  {
                Text("Under 18.5: Underweight", fontSize = 16.sp, color = Color.Black)
                Text("18.5 to 24.9: Normal weight", fontSize = 16.sp, color = Color.Black)
                Text("25 to 29.9: Overweight", fontSize = 16.sp, color = Color.Black)
                Text("30 or more: Obesity", fontSize = 16.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBMIPage() {
    BMIPage(navController = rememberNavController(),onBackClick = {})
}