package com.aravind.composefitnessapp.ui.screen.progressphoto

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext



// بيانات الشاشة
data class AlbumViewState(
    val tempFileUrl: Uri? = null,
    val monthlyPhotos: List<androidx.compose.ui.graphics.ImageBitmap?> = List(8) { null }
)

// أحداث المستخدم
sealed class Intent {
    data class OnPermissionGrantedWith(val context: Context, val monthIndex: Int) : Intent()
    data class OnFinishPickingImagesWith(val context: Context, val uri: Uri, val monthIndex: Int) : Intent()
    data class OnImageSavedWith(val context: Context, val monthIndex: Int) : Intent()
    object OnImageSavingCanceled : Intent()
}

class AlbumViewModel(private val coroutineContext: CoroutineContext) : ViewModel() {
    private val _albumViewState = MutableStateFlow(AlbumViewState())
    val viewStateFlow: StateFlow<AlbumViewState> get() = _albumViewState

    fun onReceive(intent: Intent) = viewModelScope.launch(coroutineContext) {
        when (intent) {
            is Intent.OnPermissionGrantedWith -> {
                val tempFile = File.createTempFile("temp_image_", ".jpg", intent.context.cacheDir)
                val uri = FileProvider.getUriForFile(
                    intent.context,
                    "com.aravind.composefitnessapp.provider", // استخدم اسم الحزمة الخاص بك هنا
                    tempFile
                )
                _albumViewState.value = _albumViewState.value.copy(tempFileUrl = uri)
            }

            is Intent.OnFinishPickingImagesWith -> {
                val stream = intent.context.contentResolver.openInputStream(intent.uri)
                val bitmap = BitmapFactory.decodeStream(stream)?.asImageBitmap()
                stream?.close()

                bitmap?.let {
                    val updatedList = _albumViewState.value.monthlyPhotos.toMutableList()
                    updatedList[intent.monthIndex] = it
                    _albumViewState.value = _albumViewState.value.copy(monthlyPhotos = updatedList)
                }
            }

            is Intent.OnImageSavedWith -> {
                _albumViewState.value.tempFileUrl?.let { uri ->
                    val source = ImageDecoder.createSource(intent.context.contentResolver, uri)
                    val bitmap = ImageDecoder.decodeBitmap(source).asImageBitmap()

                    val updatedList = _albumViewState.value.monthlyPhotos.toMutableList()
                    updatedList[intent.monthIndex] = bitmap

                    _albumViewState.value = _albumViewState.value.copy(
                        monthlyPhotos = updatedList,
                        tempFileUrl = null
                    )
                }
            }

            is Intent.OnImageSavingCanceled -> {
                _albumViewState.value = _albumViewState.value.copy(tempFileUrl = null)
            }
        }
    }
}