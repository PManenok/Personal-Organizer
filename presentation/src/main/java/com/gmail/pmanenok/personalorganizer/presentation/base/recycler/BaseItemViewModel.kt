package com.gmail.pmanenok.personalorganizer.presentation.base.recycler

import android.databinding.ObservableInt
import android.view.View

abstract class BaseItemViewModel<Entity> {
    val position = ObservableInt()
    abstract fun bindItem(item: Entity, position: Int)
    open fun onItemClick() {}
}