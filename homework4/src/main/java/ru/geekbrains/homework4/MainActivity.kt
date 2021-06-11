package ru.geekbrains.homework4

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.homework4.R.layout.activity_main
import ru.geekbrains.homework4.data.Image
import ru.geekbrains.homework4.databinding.ActivityMainBinding
import ru.geekbrains.homework4.presentation.ImageConverter
import ru.geekbrains.homework4.presentation.ImagePicker
import ru.geekbrains.homework4.presentation.ImagePresenter

class MainActivity : MvpAppCompatActivity(activity_main),
    ru.geekbrains.homework4.presentation.ImageView {

    private var disposable = CompositeDisposable()

    private val presenter: ImagePresenter by moxyPresenter {
        ImagePresenter(ImageConverter(this), AndroidSchedulers.mainThread())
    }
    private var binding: ActivityMainBinding? = null
    private val activityLauncher = registerForActivityResult(MainActivityContract()) { data ->
        getImage(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun setImage(image: Image?) {
        if (image != null)
            binding?.imageView?.setImageBitmap(image.bitmap)
    }

    override fun init() {
        binding?.chooseButton?.setOnClickListener {
            presenter.convertImage()
        }
        binding?.saveButton?.setOnClickListener {
            presenter.savePngImage()
        }
    }

    override fun openImage() {
        activityLauncher.launch(null)
    }

    private fun getImage(data: Intent?) {
        val imagePicker = ImagePicker()
        disposable.add(
            imagePicker
                .pick(data, contentResolver)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { image ->
                        successPickImage(image)
                    },
                    { throwable ->
                        showError(throwable.localizedMessage)
                    }
                )
        )
    }

    private fun successPickImage(image: Image?) {
        presenter.setImage(image)
        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess() {
        Toast.makeText(this, getString(R.string.success_convert), Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}

class MainActivityContract : ActivityResultContract<Unit, Intent>() {
    override fun createIntent(context: Context, input: Unit?): Intent {
        val intent = Intent().apply {
            type = context.getString(R.string.type_image)
            action = Intent.ACTION_OPEN_DOCUMENT
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        val title = context.getString(R.string.select_image)
        return Intent.createChooser(intent, title)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Intent? = when {
        resultCode != Activity.RESULT_OK -> null
        else -> intent
    }
}