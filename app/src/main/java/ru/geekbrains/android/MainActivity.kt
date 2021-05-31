package ru.geekbrains.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.geekbrains.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    val presenter = Presenter(this, CountersModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener {
            presenter.incrementCounter(0)
        }
        vb?.btnCounter2?.setOnClickListener {
            presenter.incrementCounter(1)
        }
        vb?.btnCounter3?.setOnClickListener {
            presenter.incrementCounter(2)
        }
    }

    override fun showCounter(id: Int, counter: Int) {
        when(id){
            0 -> vb?.btnCounter1?.text = "$counter"
            1 -> vb?.btnCounter2?.text = "$counter"
            2 -> vb?.btnCounter3?.text = "$counter"
        }
    }
}