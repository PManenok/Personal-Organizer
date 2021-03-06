package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.binding

import android.databinding.ObservableField
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.Record
import com.gmail.pmanenok.personalorganizer.presentation.base.recycler.BaseItemViewModel

class RecordItemViewModel : BaseItemViewModel<Record>() {
    val id = ObservableField<String>("id")
    val type = ObservableField<String>("type")
    val title = ObservableField<String>("title")
    val comment = ObservableField<String>("comment")

    override fun bindItem(item: Record, position: Int) {
        this.position.set(position)
        id.set(item.id)
        type.set(item.type.name)
        title.set(item.title)
        comment.set(item.comment)
    }
}