package com.gmail.pmanenok.personalorganizer.presentation.screen.note.birthdayrec

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter

class BirthdayRecordViewModel : BaseViewModel<NoteRouter>() {
    val title = ObservableField<String>("title")
    val comment = ObservableField<String>("comment")
    val notify = ObservableBoolean(false)
}