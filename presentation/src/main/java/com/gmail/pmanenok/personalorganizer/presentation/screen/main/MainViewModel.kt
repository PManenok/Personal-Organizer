package com.gmail.pmanenok.personalorganizer.presentation.screen.main

import android.support.v7.widget.PopupMenu
import android.view.View
import com.gmail.pmanenok.domain.entity.NoteType
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel

class MainViewModel : BaseViewModel<MainRouter>() {
    val newNoteOnClick = View.OnClickListener {
        router?.goToNoteActivity(NoteType.TYPE_NOTE)
    }
    val searchOnClick = View.OnClickListener {
        router?.goToSearchActivity()
    }
    val settingsOnClick = View.OnClickListener {
        router?.goToSettingsActivity()
    }
    val newNoteMenuListener = PopupMenu.OnMenuItemClickListener {
        when (it.itemId) {
            R.id.new_note -> {
                router?.goToNoteActivity(NoteType.TYPE_NOTE)
                true
            }
            R.id.new_birthday -> {
                router?.goToNoteActivity(NoteType.TYPE_BIRTHDAY)
                true
            }
            R.id.new_list -> {
                router?.goToNoteActivity(NoteType.TYPE_LIST)
                true
            }
            else -> {
                false
            }
        }
    }
}