package com.gmail.pmanenok.personalorganizer.presentation.screen.main.month

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentMonthBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import kotlinx.android.synthetic.main.fragment_month.*


class MonthFragment : BaseMvvmFragment<MonthViewModel, MainRouter, FragmentMonthBinding>() {
    companion object {
        fun getInstance(): MonthFragment {
            return MonthFragment()
        }
    }

    override fun prodiveViewModel(): MonthViewModel {
        return ViewModelProviders.of(this).get(MonthViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_month

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("MonthFragment", "onActivityCreated")
        calendar_view.setCellOnClickListener(viewModel.cellOnClickListener)
        calendar_view.adapter = viewModel.adapter
        calendar_view.setOnLongClickListener(viewModel.calendarLongClickListener)
    }

    override fun onResume() {
        super.onResume()
        Log.e("MonthFragment", "onResume refresh")
        viewModel.refresh()
    }
}