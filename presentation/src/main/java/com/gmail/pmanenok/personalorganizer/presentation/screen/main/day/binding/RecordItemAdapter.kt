package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.binding

import android.view.ViewGroup
import com.gmail.pmanenok.domain.entity.Record
import com.gmail.pmanenok.personalorganizer.presentation.base.recycler.BaseRecyclerAdapter

class RecordItemAdapter(onItemClick: (Record) -> Unit) :
BaseRecyclerAdapter<Record, RecordItemViewModel>(onItemClick = onItemClick) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecordItemViewHolder {
        return RecordItemViewHolder.create(viewGroup, RecordItemViewModel())
    }
}