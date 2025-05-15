package com.aravind.composefitnessapp.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.theme.Black

@Composable
fun ContactUsScreen(onBack: () -> Unit) {

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
                .padding(20.dp, top = 40.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Contact Us",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
            )
            Spacer(modifier = Modifier.height(24.dp))

            ContactCard(title = "Email", value = "mostafashams000444@gmail.com")
            Spacer(modifier = Modifier.height(16.dp))
            ContactCard(title = "Alternate Email", value = "support@example.com")
            Spacer(modifier = Modifier.height(16.dp))
            ContactCard(title = "Phone", value = "+20 109 789 8248")
            Spacer(modifier = Modifier.height(16.dp))
            ContactCard(title = "Alternate Phone", value = "+20 111 222 3333")
            Spacer(modifier = Modifier.height(16.dp))
            ContactCard(title = "Address", value = "Cairo, Egypt")
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

@Composable
fun ContactCard(title: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactUsScreenPreview() {
    ContactUsScreen(onBack={})
}