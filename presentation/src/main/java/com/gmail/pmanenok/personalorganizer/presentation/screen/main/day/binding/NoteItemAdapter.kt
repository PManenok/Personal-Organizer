package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.binding

import android.view.View
import android.view.ViewGroup
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.personalorganizer.presentation.base.recycler.BaseRecyclerAdapter

class NoteItemAdapter(onItemClick: (Note) -> Unit) :
BaseRecyclerAdapter<Note, NoteItemViewModel>(onItemClick = onItemClick) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoteItemViewHolder {
        return NoteItemViewHolder.create(viewGroup, NoteItemViewModel())
    }
}