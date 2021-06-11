package ru.geekbrains.homework4.presentation

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import ru.geekbrains.homework4.data.Image

class ImagePresenter(
    private val converter: ImageConverter,
    private val uiScheduler: Scheduler
) : MvpPresenter<ImageView>() {

    private var disposable = CompositeDisposable()
    private var image: Image? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setImage(image)
    }

    fun convertImage() {
        viewState.openImage()
    }

    fun setImage(image: Image?) {
        this.image = image
        viewState.setImage(image)
    }

    fun savePngImage() {
        disposable.add(
            converter
                .convert(image)
                .observeOn(uiScheduler)
                .subscribe(
                    {
                        successSavePngImage()
                    },
                    { throwable ->
                        errorSavePngImage(throwable.localizedMessage)
                    }
                )
        )
    }

    private fun successSavePngImage() {
        viewState.showSuccess()
    }

    private fun errorSavePngImage(message: String?) {
        viewState.showError(message)
    }
}