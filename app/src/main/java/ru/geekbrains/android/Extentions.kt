package ru.geekbrains.android

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun View.click(click: () -> Unit) = setOnClickListener { click() }

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}