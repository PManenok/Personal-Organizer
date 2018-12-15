package com.gmail.pmanenok.personalorganizer.presentation.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*
import com.gmail.pmanenok.personalorganizer.R
import java.lang.ref.WeakReference

/* class TabLinearLayout contains tabs for ViewPager and LinearTabOnPageChangeListener */
class TabLinearLayout : LinearLayout {
    private val tabs: ArrayList<TabLinearLayout.TabTextView> = arrayListOf()
    private var selectedTab: TabLinearLayout.TabTextView? = null
    private var viewPager: ViewPager? = null
    private var pageChangeListener: LinearTabOnPageChangeListener? = null
    private var tabCount = 0

    /* Attribute values */
    private var tabSelectedColor = Color.TRANSPARENT
    private var tabTextGravity = Gravity.NO_GRAVITY

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        initAttrs(attrs)
    }

    /*  Initialize attributes from XML file  */
    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabLinearLayout)
        tabSelectedColor =
                typedArray.getColor(R.styleable.TabLinearLayout_linearTabSelectedTextColor, Color.TRANSPARENT)
        tabTextGravity = typedArray.getInteger(R.styleable.TabLinearLayout_linearTabTextGravity, Gravity.NO_GRAVITY)
        typedArray.recycle()
    }

    /* inner class TabTextView is simple TextView but contains its position as a tab */
    inner class TabTextView(context: Context?) : TextView(context) {
        var position: Int = -1
    }

    fun setupWithViewPager(newViewPager: ViewPager?) {
        if (pageChangeListener != null) {
            viewPager?.removeOnPageChangeListener(pageChangeListener!!)
        }

        if (newViewPager != null) {
            this.viewPager = newViewPager
            if (this.pageChangeListener == null) {
                this.pageChangeListener = LinearTabOnPageChangeListener(this)
            }

            this.pageChangeListener!!.reset()
            newViewPager.addOnPageChangeListener(this.pageChangeListener!!)
            setTabs()
        } else {
            this.viewPager = null
            tabs.clear()
            selectedTab = null
        }
    }

    private fun setTabs() {
        tabCount = 0
        while (tabCount < viewPager?.adapter?.count ?: 0) {
            val tab = TabTextView(context)
            tab.text = viewPager?.adapter?.getPageTitle(tabCount)
            tab.position = tabCount
            tab.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f)
            tab.gravity = tabTextGravity
            tab.setOnClickListener { viewPager?.currentItem = tab.position }
            tabCount++
            tabs.add(tab)
        }
        selectTab(tabs[0])
    }

    override fun invalidate() {
        super.invalidate()
        removeAllViews()
        for (tab in tabs) {
            if (tab == selectedTab)
                tab.setBackgroundColor(tabSelectedColor)
            addView(tab)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        this.setupWithViewPager(null)
    }

    private fun getTabAt(position: Int): TabTextView {
        return tabs[position]
    }

    private fun selectTab(tab: TabTextView) {
        if (selectedTab != tab) {
            selectedTab?.setBackgroundColor(Color.TRANSPARENT)
            selectedTab = tab
        }
        invalidate()
    }

    /* Listens changes in ViewPager like scrolling and selecting different pages */
    inner class LinearTabOnPageChangeListener(tabLayout: TabLinearLayout) : ViewPager.OnPageChangeListener {
        private val tabLayoutRef: WeakReference<TabLinearLayout> = WeakReference(tabLayout)
        private var previousScrollState: Int = 0
        private var scrollState: Int = 0

        override fun onPageScrollStateChanged(state: Int) {
            this.previousScrollState = this.scrollState
            this.scrollState = state
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            val tabLayout = this.tabLayoutRef.get()
            if (tabLayout != null && tabLayout.selectedTab?.position != position && position < tabLayout.tabCount) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        }

        fun reset() {
            this.scrollState = 0
            this.previousScrollState = this.scrollState
        }
    }
}
