package com.gmail.pmanenok.personalorganizer.presentation.screen.main

import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.ActivityMainBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmActivity
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.DayFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.month.MonthFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_main_top_bar.*
import kotlinx.android.synthetic.main.include_main_top_bar.view.*


class MainActivity : BaseMvvmActivity<MainViewModel, MainRouter, ActivityMainBinding>() {
    var portrait: Boolean = true
    override fun prodiveViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun provideRouter(): MainRouter {
        return MainRouter(this)
    }

    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "onCreate")
        val newBarBtn = main_bar_new_img_btn
        newBarBtn.setOnClickListener { router.goToNewNoteActivity() }
        val searchBarBtn = main_bar_search_img_btn
        searchBarBtn.setOnClickListener { router.goToSearchActivity() }
        val settingsBarBtn = main_bar_settings_img_btn
        settingsBarBtn.setOnClickListener { router.goToSettingsActivity() }
        portrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        mPager = main_view_pager

        // The pager adapter, which provides the pages to the view pager widget.
        val adapter = MainViewPagerAdapter(this, supportFragmentManager)
        adapter.addPage(
            MonthFragment(),
            resources.getString(R.string.pager_month).toUpperCase(), R.drawable.ic_month_30dp
        )
        adapter.addPage(Fragment(), resources.getString(R.string.pager_week).toUpperCase(), R.drawable.ic_week_30dp)
        adapter.addPage(DayFragment(), resources.getString(R.string.pager_day).toUpperCase(), R.drawable.ic_day_30dp)
        mPager.adapter = adapter

        if (portrait) {
            val tabLayout = main_tab_layout
            tabLayout.setupWithViewPager(mPager)
        } else {
            main_tab_linear_layout.setupViewPager(mPager)
        }
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }
    }
}
