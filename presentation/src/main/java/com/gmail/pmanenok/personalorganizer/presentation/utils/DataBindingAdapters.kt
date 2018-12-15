package com.gmail.pmanenok.personalorganizer.presentation.utils

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("onClick")
fun View.onClick(listener: View.OnClickListener) {
    setOnClickListener(listener)
}