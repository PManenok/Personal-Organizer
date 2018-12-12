package com.gmail.pmanenok.personalorganizer.presentation.screen.search

import android.arch.lifecycle.ViewModelProviders
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.ActivityNoteBinding
import com.gmail.pmanenok.personalorganizer.databinding.ActivitySearchBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmActivity

class SearchActivity : BaseMvvmActivity<SearchViewModel, SearchRouter, ActivitySearchBinding>() {
    override fun prodiveViewModel(): SearchViewModel {
        return ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_note
    }

    override fun provideRouter(): SearchRouter {
        return SearchRouter(this)
    }
}