package ru.geekbrains.homework4.presentation

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.homework4.data.Image

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageView : MvpView {

    fun setImage(image: Image?)

    fun init()

    fun openImage()

    fun showSuccess()

    fun showError(errorMessage: String?)
}