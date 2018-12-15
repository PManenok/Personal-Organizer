package com.gmail.pmanenok.personalorganizer.presentation.screen.note.birthdayrec

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentBirthdayRecordBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.listrec.ListRecordFragment


class BirthdayRecordFragment : BaseMvvmFragment<BirthdayRecordViewModel, NoteRouter, FragmentBirthdayRecordBinding>() {
    companion object {
        private const val NOTE_ID_EXTRA = "NOTE_ID_EXTRA"
        fun getInstance(id: String? = null): BirthdayRecordFragment {
            val fragment = BirthdayRecordFragment()
            val bundle = Bundle()
            if (id != null)
                bundle.putString(NOTE_ID_EXTRA, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): BirthdayRecordViewModel {
        return ViewModelProviders.of(this).get(BirthdayRecordViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_birthday_record

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(NOTE_ID_EXTRA) ?: NOTE_ID_EXTRA
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