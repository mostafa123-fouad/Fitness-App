package com.aravind.composefitnessapp.ui.screen.progressphoto

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
import com.aravind.composefitnessapp.ui.theme.PinkLight
import com.aravind.composefitnessapp.ui.theme.PurpleLight
import com.aravind.composefitnessapp.ui.theme.RedDanger
import com.aravind.composefitnessapp.ui.theme.poppinsFamily
import com.google.firebase.perf.util.Timer

@Composable
fun ProgressPhotoScreen(navController: NavController) {


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
                        val cardShape = RoundedCornerShape(16.dp)
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                "Progress Photo",
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
                                    .padding(
                                        start = 13.dp,
                                        end = 13.dp,
                                        top = 16.dp,
                                        bottom = 16.dp
                                    )
                                    .clickable { println("Button Clicked!") })
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .background(color = PinkLight, shape = RoundedCornerShape(20.dp))
                                .padding(18.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_date_remainder),
                                contentDescription = null,
                                modifier = Modifier
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    "Reminder!",
                                    color = RedDanger,
                                    fontSize = 12.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Normal
                                )
                                Text(
                                    "Next Photos Fall On July 08",
                                    color = Black,
                                    fontSize = 14.sp,
                                    fontFamily = poppinsFamily,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1
                                )
                            }
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Icon",
                                tint = Grey1
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = PurpleLight, shape = RoundedCornerShape(12.dp))
                                .padding(15.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(end = 15.dp)
                                    .weight(0.5f)
                            ) {
                                Text(buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 12.sp,
                                            fontFamily = poppinsFamily,
                                            fontWeight = FontWeight.Medium,
                                            color = Black
                                        ),
                                    ) {
                                        append("Track Your Progress Each Month With ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 12.sp,
                                            fontFamily = poppinsFamily,
                                            fontWeight = FontWeight.Medium,
                                            brush = Brush.horizontalGradient(
                                                listOf(
                                                    GradientStart,
                                                    GradientEnd
                                                )
                                            )
                                        )
                                    ) {
                                        append("Photo")
                                    }
                                })
                                Spacer(modifier = Modifier.height(20.dp))
                                Button(

                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(start = 0.dp, end = 0.dp, bottom = 0.dp)
                                        .background(
                                            brush = Brush.horizontalGradient(
                                                listOf(
                                                    GradientStart,
                                                    GradientEnd
                                                )
                                            ),
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .height(30.dp),
                                    onClick = {
                                        navController.navigate(Screen.Tracker.route)
                                    },
                                    contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                                ) {
                                    Text(
                                        "Learn More", color = Color.White, fontSize = 12.sp,
                                        fontFamily = poppinsFamily, fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                            Image(
                                painter = painterResource(id = R.drawable.ic_progress_photo_date),
                                contentDescription = null,
                                modifier = Modifier
                            )

                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = PurpleLight, shape = RoundedCornerShape(12.dp))
                                .padding(15.dp)
                        ) {
                            Text(
                                "Compare my photo", color = Black, fontSize = 14.sp,
                                fontFamily = poppinsFamily, fontWeight = FontWeight.Medium
                            )
                            Button(

                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(start = 0.dp, end = 0.dp, bottom = 0.dp)
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(
                                                GradientStart,
                                                GradientEnd
                                            )
                                        ),
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .height(30.dp),
                                onClick = {
                                    navController.navigate(Screen.Compare.route)
                                },
                                contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                            ) {
                                Text(
                                    "Compare", color = Color.White, fontSize = 12.sp,
                                    fontFamily = poppinsFamily, fontWeight = FontWeight.Normal
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(25.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Gallery",
                                color = Black,
                                fontSize = 16.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "See more",
                                color = Black,
                                fontSize = 12.sp,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "June 2",
                            color = Black,
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(space = 13.dp),

                            ) {
                            items(4) { item ->

                                Image(
                                    painter = painterResource(id = R.drawable.ic_workout),
                                    contentDescription = null, modifier = Modifier.size(100.dp)
                                )
//                            GifImage(
//                                gifResourceId = R.drawable.exersice_1, // GIF الصورة هنا
//                                modifier = Modifier.size(100.dp)
//                            )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "May 5",
                            color = Black,
                            fontSize = 12.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(space = 13.dp),

                            ) {
                            items(4) { item ->
                                Image(
                                    painter = painterResource(id = R.drawable.ic_workout),
                                    contentDescription = null, modifier = Modifier.size(100.dp)
                                )
                            }
                        }


                    }
                }
            })
    }
}


@Composable
@Preview
fun ProgressPhotoScreenPreview() {
    ProgressPhotoScreen(navController = rememberNavController())
}
///////////////////////////////////////////////////////gif
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

//////////////////////////////////////////////////////// compare photo




