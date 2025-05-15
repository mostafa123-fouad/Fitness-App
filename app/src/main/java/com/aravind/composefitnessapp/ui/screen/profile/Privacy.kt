package com.aravind.composefitnessapp.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.theme.Black

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {

        }
    ) { paddingValues ->

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
                    .padding(paddingValues)
                    .padding(50.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Privacy Policy",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "We collect basic personal information (like your name, email, and fitness goals) to provide and improve our app services.\n\n" +
                            "We do not sell your data. We may share data with trusted services (like analytics) to help improve your experience.\n\n" +
                            "You can manage or delete your data anytime.\n\n" +
                            "By using our app, you agree to this policy.\n\n",
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }

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
        }

    }
}
@Preview
@Composable
fun Showing(){
    PrivacyPolicyScreen(onBack={})
}