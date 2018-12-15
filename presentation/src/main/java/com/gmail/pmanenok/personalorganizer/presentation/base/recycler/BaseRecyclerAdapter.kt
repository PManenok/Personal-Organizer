package com.gmail.pmanenok.personalorganizer.presentation.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.subjects.PublishSubject

abstract class BaseRecyclerAdapter<Entity, VM : BaseItemViewModel<Entity>>
    (val itemList: MutableList<Entity> = mutableListOf(), val onItemClick: (Entity) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<Entity, VM, *>>() {

    val clickItemSubject = PublishSubject.create<ItemClick<Entity>>()

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder<Entity, VM, *>, position: Int) {
        holder.bind(itemList[position], position)
    }

    fun addItems(items: List<Entity>) {
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val pos = holder.adapterPosition
            clickItemSubject.onNext(ItemClick(itemList[pos], pos))
            holder.viewModel.onItemClick()
            onItemClick(itemList[pos])
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }
}