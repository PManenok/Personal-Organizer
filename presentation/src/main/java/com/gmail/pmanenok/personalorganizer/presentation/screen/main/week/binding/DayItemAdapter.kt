package com.gmail.pmanenok.personalorganizer.presentation.screen.main.week.binding

import android.view.ViewGroup
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.personalorganizer.presentation.base.recycler.BaseRecyclerAdapter

class DayItemAdapter(onItemClick: (Pair<Long, List<String>>) -> Unit) :
BaseRecyclerAdapter<Pair<Long, List<String>>, DayItemViewModel>(onItemClick = onItemClick) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DayItemViewHolder {
        return DayItemViewHolder.create(viewGroup, DayItemViewModel())
    }
}