package com.gmail.pmanenok.personalorganizer.presentation.screen.main

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.text.Spannable
import android.text.SpannableString
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteActivity
import com.gmail.pmanenok.personalorganizer.presentation.screen.search.SearchActivity
import com.gmail.pmanenok.personalorganizer.presentation.screen.settings.SettingsActivity


class MainRouter(activity: MainActivity) : BaseRouter<MainActivity>(activity) {
    val adapter = MainPagerAdapter(activity.supportFragmentManager)

    inner class MainPagerAdapter(fM: FragmentManager) : FragmentPagerAdapter(fM) {
        private val imageResId = mutableListOf<Int>()
        private val pages = mutableListOf<Fragment>()
        private val titles = mutableListOf<String>()

        fun addPage(fragment: Fragment, title: String = fragment.javaClass.simpleName, imageId: Int = 0) {
            pages.add(fragment)
            imageResId.add(imageId)
            titles.add(title)
        }

        override fun getItem(position: Int): Fragment = pages[position]

        override fun getCount(): Int = pages.size

        override fun getPageTitle(position: Int): CharSequence? {
            if (imageResId[position] != 0) {
                val sb: SpannableString = if (activity.portrait) SpannableString("  " + titles[position])
                else SpannableString(" ")

                val imageSpan = ImageSpan(activity, imageResId[position], DynamicDrawableSpan.ALIGN_BOTTOM)
                sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                return sb
            }
            return titles[position]
        }
    }

    fun goToNewNoteActivity() {
        val intent = Intent(activity, NoteActivity::class.java)
        activity.startActivity(intent)
    }
    fun goToSearchActivity() {
        val intent = Intent(activity, SearchActivity::class.java)
        activity.startActivity(intent)
    }
    fun goToSettingsActivity() {
        val intent = Intent(activity, SettingsActivity::class.java)
        activity.startActivity(intent)
    }

    /*  fun goToStudentList() {
          Log.e("bbb", "router goToStudentList")
          replaceFragment(
              activity.supportFragmentManager,
              StudentListFragment.getInstance(),
              R.id.student_container,
              false
          )
      }*/

}