package ru.geekbrains.homework4.data

import android.content.ContentResolver
import android.content.Intent
import io.reactivex.rxjava3.core.Single

interface Picker {
    fun pick(data: Intent?, contentResolver: ContentResolver): Single<Image?>
}