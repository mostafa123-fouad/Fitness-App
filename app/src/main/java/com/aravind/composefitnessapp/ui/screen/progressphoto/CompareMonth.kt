package com.aravind.composefitnessapp.ui.screen.progressphoto

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aravind.composefitnessapp.ui.theme.Black
import java.util.Collections.addAll


@Composable
fun MonthlyProgressScreen(
    viewState: AlbumViewState,
    onTakePhotoClick: (Int) -> Unit,
    onPickPhotoClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("monthly_photos", Context.MODE_PRIVATE) }

    val monthlyBitmaps = remember { mutableStateListOf<Bitmap?>().apply {
        addAll(List(8) { null }) // 8 أشهر
    } }

    val selectedMonth by remember { mutableStateOf("January") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val currentMonthIndex by remember { mutableStateOf<Int?>(null) }
    //val afterBitmap = remember { mutableStateOf<Bitmap?>(null) }

    // Launcher to pick from gallery
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val index = currentMonthIndex
            if (index != null) {
                val bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
                monthlyBitmaps[index] = bitmap
                prefs.edit().putString("month_$index", it.toString()).apply()
                onPickPhotoClick(index)
            }
        }
    }

    // Launcher to take photo
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bmp ->
        bmp?.let {

            currentMonthIndex?.let {
                monthlyBitmaps[it] = bitmap
                onTakePhotoClick(it)
            }
            // You can save to file if you want persistence
        }
    }

    // Load saved image
    LaunchedEffect(selectedMonth) {
        prefs.getString(selectedMonth, null)?.let {
            imageUri = Uri.parse(it)
        }
    }

    // Convert Uri to Bitmap
    LaunchedEffect(imageUri) {
        imageUri?.let {
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    // تحميل الصور المحفوظة عند البداية
    LaunchedEffect(Unit) {
        for (i in 0 until 8) {
            prefs.getString("month_$i", null)?.let { uriString ->
                val uri = Uri.parse(uriString)
                val bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                }
                monthlyBitmaps[i] = bitmap
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Track Your Photos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 14.dp, start = 90.dp),
            color=Color.White
        )

        repeat(8) { monthIndex ->

            val pickPhotoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                uri?.let {
                    val bitmap = if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        ImageDecoder.decodeBitmap(source)
                    }
                    monthlyBitmaps[monthIndex] = bitmap
                    prefs.edit().putString("month_$monthIndex", it.toString()).apply()
                    onPickPhotoClick(monthIndex)
                }
            }

            val takePhotoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bmp ->
                bmp?.let {
                    monthlyBitmaps[monthIndex] = it
                    onTakePhotoClick(monthIndex)
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    if (monthlyBitmaps[monthIndex] != null) {
                        Image(
                            bitmap = monthlyBitmaps[monthIndex]!!.asImageBitmap(),
                            contentDescription = "Month ${monthIndex + 1} Photo",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                    else {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.Gray.copy(alpha = 0.3f))
                                .clip(RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No Photo", fontSize = 12.sp, color = Color.Gray)
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text("Month ${monthIndex + 1}", style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(
                                onClick = {
                                    takePhotoLauncher.launch()
                                    onTakePhotoClick(monthIndex)
                                          },
                                modifier = Modifier.padding(2.dp)
                            ) {
                                Text("Take Photo")
                            }

                            Button(onClick = {
                                pickPhotoLauncher.launch("image/*")
                                onPickPhotoClick(monthIndex) }) {
                                Text("Pick Photo")
                            }
                        }

                        bitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "Monthly Photo",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                            )
                        }
                    }
                }
            }
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

@Preview(showBackground = true)
@Composable
fun MonthlyProgressScreenPreview() {
    val dummyState = AlbumViewState(
        monthlyPhotos = List(8) { index ->
            if (index % 2 == 0) null else ImageBitmap(100, 100)
        }
    )

    MonthlyProgressScreen(
        viewState = dummyState,
        onTakePhotoClick = {},
        onPickPhotoClick = {},
        onBack={}
    )
}