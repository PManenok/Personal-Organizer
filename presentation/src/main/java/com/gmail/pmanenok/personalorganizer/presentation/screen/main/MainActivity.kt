package com.gmail.pmanenok.personalorganizer.presentation.screen.main

import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.PopupMenu
import android.util.Log
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.ActivityMainBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmActivity
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.day.DayFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.month.MonthFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_main_top_bar.*
import android.view.View
import android.widget.ImageButton
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.week.WeekFragment
import java.util.*

class MainActivity : BaseMvvmActivity<MainViewModel, MainRouter, ActivityMainBinding>() {
    companion object {
        private const val NEW_NOTE_BUTTON_MENU = "NEW_NOTE_BUTTON_MENU"
        private const val SEARCH_BUTTON_MENU = "SEARCH_BUTTON_MENU"
    }

    private var portrait: Boolean = true
    private lateinit var newBarBtn: ImageButton
    private lateinit var searchBarBtn: ImageButton
    private var pagesCount: Int = 0
    lateinit var viewPager: ViewPager

    override fun prodiveViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun provideRouter(): MainRouter {
        return MainRouter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newBarBtn = main_bar_new_img_btn
        searchBarBtn = main_bar_search_img_btn
        portrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        viewPager = main_view_pager
        viewPager.offscreenPageLimit = 2
        setLongClickListeners()

        // The pager adapter, which provides the pages to the view pager widget.
        val adapter = MainViewPagerAdapter(this, supportFragmentManager)
        adapter.addPage(
            MonthFragment(),
            resources.getString(R.string.pager_month).toUpperCase(),
            R.drawable.ic_month_30dp
        )
        adapter.addPage(
            WeekFragment(),
            resources.getString(R.string.pager_week).toUpperCase(),
            R.drawable.ic_week_30dp
        )
        adapter.addPage(
            DayFragment(),
            resources.getString(R.string.pager_day).toUpperCase(),
            R.drawable.ic_day_30dp
        )
        pagesCount = adapter.count
        viewPager.adapter = adapter
        /*viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageScrollStateChanged(position: Int) {
            }

            override fun onPageSelected(position: Int) {

                if (pagesCount == 3 && position == 1) {
                    adapter.getItem(position).onPause()
                    adapter.getItem(position).onResume()
                }
            }
        })*/

        if (portrait) main_tab_layout.setupWithViewPager(viewPager)
        else main_tab_linear_layout.setupWithViewPager(viewPager)
    }

    private fun setLongClickListeners() {
        newBarBtn.setOnLongClickListener {
            showPopup(it, NEW_NOTE_BUTTON_MENU)
            return@setOnLongClickListener true
        }
        searchBarBtn.setOnLongClickListener {
            showPopup(it, SEARCH_BUTTON_MENU)
            return@setOnLongClickListener true
        }
    }

    private fun showPopup(v: View, menu: String) {
        val popup = PopupMenu(this, v)
        when (menu) {
            NEW_NOTE_BUTTON_MENU -> {
                popup.setOnMenuItemClickListener(viewModel.newNoteMenuListener);
                popup.inflate(R.menu.menu_add_note)
            }
            SEARCH_BUTTON_MENU -> {
            }
        }
        popup.show()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    fun showDay() {
        if (viewPager.adapter?.count ?: 0 > 0) {
            viewPager.currentItem = pagesCount - 1
        }
    }
}
