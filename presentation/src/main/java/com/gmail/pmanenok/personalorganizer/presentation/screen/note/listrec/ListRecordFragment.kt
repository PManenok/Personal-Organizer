package com.gmail.pmanenok.personalorganizer.presentation.screen.note.listrec

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentListRecordBinding
import com.gmail.pmanenok.personalorganizer.databinding.FragmentMonthBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter
import kotlinx.android.synthetic.main.fragment_month.*


class ListRecordFragment : BaseMvvmFragment<ListRecordViewModel, NoteRouter, FragmentListRecordBinding>() {
    override fun prodiveViewModel(): ListRecordViewModel {
        return ViewModelProviders.of(this).get(ListRecordViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_list_record

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}