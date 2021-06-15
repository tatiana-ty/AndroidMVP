package ru.geekbrains.homework4.data

import io.reactivex.rxjava3.core.Completable

interface Converter {

    fun convert(image: Image?): Completable

}