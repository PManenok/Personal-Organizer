package com.gmail.pmanenok.personalorganizer.presentation.screen.note.birthdayrec

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentBirthdayRecordBinding
import com.gmail.pmanenok.personalorganizer.databinding.FragmentMonthBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter


class BirthdayRecordFragment : BaseMvvmFragment<BirthdayRecordViewModel, NoteRouter, FragmentBirthdayRecordBinding>() {
    override fun prodiveViewModel(): BirthdayRecordViewModel {
        return ViewModelProviders.of(this).get(BirthdayRecordViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_birthday_record

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}