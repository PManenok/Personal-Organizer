package com.gmail.pmanenok.personalorganizer.presentation.screen.note.listrec

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentListRecordBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.noterec.NoteRecordFragment


class ListRecordFragment : BaseMvvmFragment<ListRecordViewModel, NoteRouter, FragmentListRecordBinding>() {
    companion object {
        private const val NOTE_ID_EXTRA = "NOTE_ID_EXTRA"
        private const val NOTE_DAY_EXTRA = "NOTE_DAY_EXTRA"
        private const val DEFAULT_DAY_LONG = 0L
        fun getInstance(id: String? = null, day: Long = DEFAULT_DAY_LONG): ListRecordFragment {
            val fragment = ListRecordFragment()
            val bundle = Bundle()
            if (id != null)
                bundle.putString(NOTE_ID_EXTRA, id)
            bundle.putLong(NOTE_DAY_EXTRA, day)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): ListRecordViewModel {
        return ViewModelProviders.of(this).get(ListRecordViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_list_record

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(NOTE_ID_EXTRA) ?: NOTE_ID_EXTRA
        viewModel.day = arguments?.getLong(NOTE_DAY_EXTRA) ?: DEFAULT_DAY_LONG
        if (id != NOTE_ID_EXTRA) {
            //binding.noteTopBarInclude.imageButton.setOnClickListener(viewModel.backOnClick)
            //            binding.noteTopBarInclude.imageButton2.setOnClickListener(viewModel.notifyOnClick)
            //viewModel.setRecordId(id)
        } else {
            //router?.goBack()
        }
        router?.goBack()
    }

}