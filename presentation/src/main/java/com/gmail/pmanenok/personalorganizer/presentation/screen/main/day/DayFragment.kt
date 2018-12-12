package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Spannable
import android.text.SpannableString
import android.text.format.DateFormat
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentDayBinding
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import java.util.*

class DayFragment : BaseMvvmFragment<DayViewModel, MainRouter, FragmentDayBinding>() {
    companion object {
        private const val DAY_EXTRA = "DAY_EXTRA"
        fun getInstance(id: Long): DayFragment {
            val fragment = DayFragment()
            val bundle = Bundle()
            bundle.putLong(DAY_EXTRA, id)
            fragment.arguments = bundle
            fragment.dayDate = id
            return fragment
        }
    }

    private var dayDate: Long = todayFromCalendar()

    override fun prodiveViewModel(): DayViewModel {
        return ViewModelProviders.of(this).get(DayViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_day

    private fun todayFromCalendar(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getLong(DAY_EXTRA) ?: dayDate
        Log.e("TODAY", dayDate.toString())
        viewModel.dayId = id
        binding.dayTitleTv.text = DateFormat.format("d MMMM yyyy", id)
        binding.listRecyclerView.adapter = viewModel.adapter
        binding.listRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.setHasFixedSize(true)
        viewModel.get()
    }


}