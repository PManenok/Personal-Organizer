package com.gmail.pmanenok.personalorganizer.presentation.screen.note.listrec

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentListRecordBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter


class ListRecordFragment : BaseMvvmFragment<ListRecordViewModel, NoteRouter, FragmentListRecordBinding>() {
    companion object {
        private const val NOTE_ID_EXTRA = "NOTE_ID_EXTRA"
        fun getInstance(id: String? = null): ListRecordFragment {
            val fragment = ListRecordFragment()
            val bundle = Bundle()
            if (id != null)
                bundle.putString(NOTE_ID_EXTRA, id)
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
        if (id == NOTE_ID_EXTRA){

        }
    }

}