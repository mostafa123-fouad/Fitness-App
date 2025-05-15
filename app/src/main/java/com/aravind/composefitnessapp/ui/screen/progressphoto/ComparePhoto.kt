package com.aravind.composefitnessapp.ui.screen.progressphoto

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aravind.composefitnessapp.R
import com.aravind.composefitnessapp.ui.theme.Black
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ComparePhotoScreen(navController: NavController ,onBack: () -> Unit) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("compare_prefs", Context.MODE_PRIVATE) }

    var beforeUri by remember { mutableStateOf<Uri?>(null) }
    var afterUri by remember { mutableStateOf<Uri?>(null) }

    val beforeBitmap = remember { mutableStateOf<Bitmap?>(null) }
    val afterBitmap = remember { mutableStateOf<Bitmap?>(null) }

    val beforeLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            beforeUri = it
            prefs.edit().putString("before_uri", it.toString()).apply()
        }
    }

    val afterLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            afterUri = it
            prefs.edit().putString("after_uri", it.toString()).apply()
        }
    }

    val dateFormat = remember {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    }
    var currentTime by remember { mutableStateOf(dateFormat.format(Date())) }

    // تحدث الوقت تلقائيًا كل ثانية
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = dateFormat.format(Date())
            delay(1000L)
        }
    }
    // استرجاع الصور المحفوظة
    LaunchedEffect(Unit) {
        prefs.getString("before_uri", null)?.let { beforeUri = Uri.parse(it) }
        prefs.getString("after_uri", null)?.let { afterUri = Uri.parse(it) }
    }

   // تحويل URI إلى Bitmap
    LaunchedEffect(beforeUri) {
        beforeUri?.let { uri ->
            beforeBitmap.value = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            } else {
                val src = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(src)
            }
        }
    }

    LaunchedEffect(afterUri) {
        afterUri?.let { uri ->
            afterBitmap.value = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            } else {
                val src = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(src)
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





    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Compare Images  ", style = MaterialTheme.typography.headlineSmall
        , color = colorResource(R.color.white)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { beforeLauncher.launch("image/*") }) {
                Text("Before Exercise")
            }

            Button(onClick = { afterLauncher.launch("image/*") }) {
                Text("After Exercise")
            }
        }

        if (beforeBitmap.value != null && afterBitmap.value != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        bitmap = beforeBitmap.value!!.asImageBitmap(),
                        contentDescription = "Before",
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(4.dp)
                    )
                    Text(
                        text = "Date: $currentTime",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(R.color.white)
                    )

                    Button(onClick = {
                        beforeUri?.let {
                            prefs.edit().putString("before_uri", it.toString()).apply()
                        }
                    }) {
                        Text("Save")
                    }

                }



                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        bitmap = afterBitmap.value!!.asImageBitmap(),
                        contentDescription = "After",
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(4.dp)
                    )
                    Text(
                        text = "Date: $currentTime",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(R.color.white)
                    )

                    Button(onClick = {
                        afterUri?.let {
                            prefs.edit().putString("after_uri", it.toString()).apply()
                        }
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComparePhotoScreenPreview() {
    ComparePhotoScreen( navController = rememberNavController(), onBack = {})
}