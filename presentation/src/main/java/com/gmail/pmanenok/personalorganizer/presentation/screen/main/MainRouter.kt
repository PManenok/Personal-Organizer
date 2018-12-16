package com.gmail.pmanenok.personalorganizer.presentation.screen.main

import android.content.Intent
import com.gmail.pmanenok.domain.entity.NoteType
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity.Companion.NEW_NOTE
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity.Companion.NOTE_DATE_EXTRA
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity.Companion.NOTE_ID_EXTRA
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity.Companion.NOTE_MODE_EXTRA
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity.Companion.NOTE_TYPE_EXTRA
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity.Companion.OLD_NOTE
import com.gmail.pmanenok.personalorganizer.presentation.screen.search.SearchActivity
import com.gmail.pmanenok.personalorganizer.presentation.screen.settings.SettingsActivity


class MainRouter(activity: MainActivity) : BaseRouter<MainActivity>(activity) {
    fun goToNoteActivity(
        noteType: NoteType,
        id: String? = null,
        date: Long = 0L
    ) {
        val intent = Intent(activity, NoteActivity::class.java)
        intent.putExtra(NOTE_DATE_EXTRA, date)
        intent.putExtra(NOTE_TYPE_EXTRA, noteType.name)
        if (id != null) {
            intent.putExtra(NOTE_MODE_EXTRA, OLD_NOTE)
            intent.putExtra(NOTE_ID_EXTRA, id)
        } else
            intent.putExtra(NOTE_MODE_EXTRA, NEW_NOTE)
        activity.startActivity(intent)
    }

    fun goToSearchActivity() {
        val intent = Intent(activity, SearchActivity::class.java)
        activity.startActivity(intent)
    }

    fun goToSettingsActivity() {
        val intent = Intent(activity, SettingsActivity::class.java)
        activity.startActivity(intent)
    }

    fun goToDayPage() {
        activity.showDay()
    }
}