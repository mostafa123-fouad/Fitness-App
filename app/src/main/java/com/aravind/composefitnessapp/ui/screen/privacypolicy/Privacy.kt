package com.aravind.composefitnessapp.ui.screen.privacypolicy
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrivacyPolicyScreen() {
    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
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
                        "By using our app, you agree to this policy.\n\n"
                       ,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    }
}
@Preview
@Composable
fun Showing(){
    PrivacyPolicyScreen()
}
