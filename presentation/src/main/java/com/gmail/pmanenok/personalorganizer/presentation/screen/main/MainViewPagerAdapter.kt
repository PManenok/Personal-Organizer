package com.gmail.pmanenok.personalorganizer.presentation.screen.main

import android.content.Context
import android.content.res.Configuration
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.text.Spannable
import android.text.SpannableString
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan

class MainViewPagerAdapter(private val context: Context, fM: FragmentManager) : FragmentPagerAdapter(fM) {
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
            val sb: SpannableString =
                if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                    SpannableString("  " + titles[position])
                else SpannableString(" ")

            val imageSpan = ImageSpan(context, imageResId[position], DynamicDrawableSpan.ALIGN_BOTTOM)
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return sb
        }
        return titles[position]
    }
}