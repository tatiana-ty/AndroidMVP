package ru.geekbrains.homework4.presentation

import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.homework4.data.Image
import ru.geekbrains.homework4.data.Picker
import java.io.File

class ImagePicker : Picker {

    override fun pick(data: Intent?, contentResolver: ContentResolver): Single<Image?> =
        Single
            .fromCallable { getBitmap(data, contentResolver) }
            .subscribeOn(Schedulers.io())

    private fun getBitmap(data: Intent?, contentResolver: ContentResolver): Image? {
        var image: Image? = null
        data?.data?.let { uri ->
            val imageName = File(uri.path!!).name
            val bytes =
                contentResolver
                    .openInputStream(uri)
                    ?.buffered()
                    ?.use { it.readBytes() }
            bytes?.let { byteArray ->
                val bitmap =
                    BitmapFactory
                        .decodeByteArray(
                            byteArray,
                            0,
                            byteArray.size
                        )
                image = Image(bitmap, imageName)
            }
        }
        return image
    }
}