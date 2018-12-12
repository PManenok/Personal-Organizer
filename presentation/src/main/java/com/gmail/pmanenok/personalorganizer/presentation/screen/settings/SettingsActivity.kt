package com.gmail.pmanenok.personalorganizer.presentation.screen.settings

import android.arch.lifecycle.ViewModelProviders
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.ActivityNoteBinding
import com.gmail.pmanenok.personalorganizer.databinding.ActivitySettingsBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmActivity

class SettingsActivity : BaseMvvmActivity<SettingsViewModel, SettingsRouter, ActivitySettingsBinding>() {
    override fun prodiveViewModel(): SettingsViewModel {
        return ViewModelProviders.of(this).get(SettingsViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_settings
    }

    override fun provideRouter(): SettingsRouter {
        return SettingsRouter(this)
    }
}