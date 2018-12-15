package com.gmail.pmanenok.personalorganizer.presentation.screen.note

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel

class NoteViewModel : BaseViewModel<NoteRouter>() {
    val noteType = ObservableField<String>("Note type")
    val title = ObservableField<String>("title")
    val comment = ObservableField<String>("comment")
    val notify = ObservableBoolean(false)

}