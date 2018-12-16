package com.gmail.pmanenok.personalorganizer.presentation.screen.note

import android.util.Log
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity.Companion.DEFAULT_DAY_LONG
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.birthdayrec.BirthdayRecordFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.listrec.ListRecordFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.noterec.NoteRecordFragment

class NoteRouter(activity: NoteActivity) : BaseRouter<NoteActivity>(activity) {
    fun goToNoteRecord(id: String?, day: Long = DEFAULT_DAY_LONG) {
        Log.e("NoteRouter", "router goToNoteRecord")
        replaceFragment(
            activity.supportFragmentManager,
            NoteRecordFragment.getInstance(id, day),
            R.id.note_record_container,
            false
        )
    }

    fun goToListRecord(id: String?, day: Long = DEFAULT_DAY_LONG) {
        Log.e("NoteRouter", "router goToListRecord")
        replaceFragment(
            activity.supportFragmentManager,
            ListRecordFragment.getInstance(id, day),
            R.id.note_record_container,
            false
        )
    }

    fun goToBirthdayRecord(id: String?, day: Long = DEFAULT_DAY_LONG) {
        Log.e("NoteRouter", "router goToBirthdayRecord")
        replaceFragment(
            activity.supportFragmentManager,
            BirthdayRecordFragment.getInstance(id, day),
            R.id.note_record_container,
            false
        )
    }
}