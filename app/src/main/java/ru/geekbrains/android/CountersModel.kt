package ru.geekbrains.android

class CountersModel {

    val counters = mutableListOf(0, 0, 0)

    fun incrementCounter(id: Int) = ++counters[id]

}