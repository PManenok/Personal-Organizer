package com.gmail.pmanenok.personalorganizer.presentation.screen.note.noterec

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentMonthBinding
import com.gmail.pmanenok.personalorganizer.databinding.FragmentNoteRecordBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter
import kotlinx.android.synthetic.main.fragment_month.*


class NoteRecordFragment : BaseMvvmFragment<NoteRecordViewModel, NoteRouter, FragmentNoteRecordBinding>() {
    override fun prodiveViewModel(): NoteRecordViewModel {
        return ViewModelProviders.of(this).get(NoteRecordViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_note_record

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}