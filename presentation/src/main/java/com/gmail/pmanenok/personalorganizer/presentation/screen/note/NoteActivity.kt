package com.gmail.pmanenok.personalorganizer.presentation.screen.note

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.pmanenok.domain.entity.NoteType
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.ActivityNoteBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmActivity

class NoteActivity : BaseMvvmActivity<NoteViewModel, NoteRouter, ActivityNoteBinding>() {
    companion object {
        const val NOTE_TYPE_EXTRA = "NOTE_TYPE_EXTRA"
        const val NOTE_MODE_EXTRA = "NOTE_MODE_EXTRA"
        const val NEW_NOTE = "NEW_NOTE"
        const val OLD_NOTE = "OLD_NOTE"
        const val NOTE_ID_EXTRA = "NOTE_ID_EXTRA"
    }

    private var id: String? = null
    override fun prodiveViewModel(): NoteViewModel {
        return ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_note
    }

    override fun provideRouter(): NoteRouter {
        return NoteRouter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteType = NoteType.valueOf(intent.getStringExtra(NOTE_TYPE_EXTRA))
        val noteMode = intent.getStringExtra(NOTE_MODE_EXTRA)
        if (noteMode == OLD_NOTE) {
            id = intent.getStringExtra(NOTE_ID_EXTRA)
        }
        when (noteType) {
            NoteType.TYPE_NOTE -> {
                router.goToNoteRecord(id)
            }
            NoteType.TYPE_LIST -> {
                router.goToListRecord(id)
            }
            NoteType.TYPE_BIRTHDAY -> {
                router.goToBirthdayRecord(id)
            }
            else -> {
                router.goBack()
            }
        }

    }
}
