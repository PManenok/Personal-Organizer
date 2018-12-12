package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.binding

import android.databinding.ObservableField
import android.util.Log
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.personalorganizer.presentation.base.recycler.BaseItemViewModel

class NoteItemViewModel() : BaseItemViewModel<Note>() {
    val type = ObservableField<String>("type")
    val title = ObservableField<String>("title")
    val comment = ObservableField<String>("comment")

    override fun bindItem(item: Note, position: Int) {
        type.set(item.type.name)
        title.set(item.title)
        comment.set(item.comment)
    }

    /*override fun onItemClick() {
        Log.e("NoteItemViewModel", "Item clicked")

    }*/
}