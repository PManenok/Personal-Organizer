package com.gmail.pmanenok.personalorganizer.presentation.screen.note.noterec

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentNoteRecordBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter

class NoteRecordFragment : BaseMvvmFragment<NoteRecordViewModel, NoteRouter, FragmentNoteRecordBinding>() {
    companion object {
        private const val NOTE_ID_EXTRA = "NOTE_ID_EXTRA"
        fun getInstance(id: String? = null): NoteRecordFragment {
            val fragment = NoteRecordFragment()
            val bundle = Bundle()
            if (id != null)
                bundle.putString(NOTE_ID_EXTRA, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): NoteRecordViewModel {
        return ViewModelProviders.of(this).get(NoteRecordViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_note_record

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(NOTE_ID_EXTRA) ?: NOTE_ID_EXTRA
        binding.noteTopBarInclude.noteTopBarBackBtn.setOnClickListener(viewModel.backOnClick)
        binding.noteTopBarInclude.noteTopBarNotifyBtn.setOnClickListener(viewModel.notifyOnClick)
        if (id != NOTE_ID_EXTRA) {
            viewModel.setRecordId(id)
        }
    }
}