package ru.geekbrains.homework4.presentation

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.provider.MediaStore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.homework4.data.Converter
import ru.geekbrains.homework4.data.Image
import java.io.File
import java.io.FileOutputStream

class ImageConverter(private val context: Context) : Converter {

    override fun convert(image: Image?): Completable = Completable.fromAction {
        val imageName = "convert_image.png"
        val file = File(context.getExternalFilesDir(DIRECTORY_DOWNLOADS), imageName)

        val fos = FileOutputStream(file)
        image?.bitmap?.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.close()

        MediaStore.Images.Media.insertImage(
            context.contentResolver,
            file.absolutePath,
            file.name,
            file.name
        )

    }.subscribeOn(Schedulers.io())
}