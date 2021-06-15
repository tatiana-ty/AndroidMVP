package ru.gb.gb_popular_libs.scheduler

import ru.geekbrains.android.schedulers.DefaultSchedulers
import ru.geekbrains.android.schedulers.Schedulers

object SchedulersFactory {

    fun create(): Schedulers = DefaultSchedulers()

}