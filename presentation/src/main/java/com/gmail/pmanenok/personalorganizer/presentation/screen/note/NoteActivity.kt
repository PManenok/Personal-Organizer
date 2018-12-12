package com.gmail.pmanenok.personalorganizer.presentation.screen.note

import android.arch.lifecycle.ViewModelProviders
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.ActivityNoteBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmActivity

class NoteActivity : BaseMvvmActivity<NoteViewModel, NoteRouter, ActivityNoteBinding>() {
    override fun prodiveViewModel(): NoteViewModel {
        return ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_note
    }

    override fun provideRouter(): NoteRouter {
        return NoteRouter(this)
    }
}