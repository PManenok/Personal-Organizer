package com.gmail.pmanenok.personalorganizer.presentation.screen.main.week.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.personalorganizer.databinding.ItemDayListBinding
import com.gmail.pmanenok.personalorganizer.databinding.ItemNoteListBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.recycler.BaseViewHolder

class DayItemViewHolder(binding: ItemDayListBinding, viewModel: DayItemViewModel) :
    BaseViewHolder<Pair<Long, List<Note>>, DayItemViewModel, ItemDayListBinding>(binding, viewModel) {
    companion object {
        fun create(parent: ViewGroup, viewModel: DayItemViewModel): DayItemViewHolder {
            val binding = ItemDayListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DayItemViewHolder(binding, viewModel)
        }
    }
}