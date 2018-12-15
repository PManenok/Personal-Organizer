package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.Record
import com.gmail.pmanenok.personalorganizer.databinding.ItemNoteListBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.recycler.BaseViewHolder

class RecordItemViewHolder(binding: ItemNoteListBinding, viewModel: RecordItemViewModel) :
    BaseViewHolder<Record, RecordItemViewModel, ItemNoteListBinding>(binding, viewModel) {
    companion object {
        fun create(parent: ViewGroup, viewModel: RecordItemViewModel): RecordItemViewHolder {
            val binding = ItemNoteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RecordItemViewHolder(binding, viewModel)
        }
    }
}