package com.gmail.pmanenok.personalorganizer.presentation.screen.main.week

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentWeekBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter


class WeekFragment : BaseMvvmFragment<WeekViewModel, MainRouter, FragmentWeekBinding>() {
    companion object {
        private const val SELECTED_DAY_EXTRA = "SELECTED_DAY_EXTRA"
        fun getInstance(id: Long): WeekFragment {
            val fragment = WeekFragment()
            val bundle = Bundle()
            bundle.putLong(SELECTED_DAY_EXTRA, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): WeekViewModel {
        return ViewModelProviders.of(this).get(WeekViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_week

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.weekDaysRecyclerView.adapter = viewModel.adapter
        binding.weekDaysRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.weekDaysRecyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }

}