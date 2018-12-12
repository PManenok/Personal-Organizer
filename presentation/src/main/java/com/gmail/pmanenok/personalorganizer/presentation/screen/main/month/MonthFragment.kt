package com.gmail.pmanenok.personalorganizer.presentation.screen.main.month

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentMonthBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import kotlinx.android.synthetic.main.fragment_month.*


class MonthFragment : BaseMvvmFragment<MonthViewModel, MainRouter, FragmentMonthBinding>() {
    override fun prodiveViewModel(): MonthViewModel {
        return ViewModelProviders.of(this).get(MonthViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_month

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("MonthFragment", "onActivityCreated")
        calendar_view.adapter = viewModel.adapter


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("MonthFragment", "onViewCreated")
        //calendar.adapter = viewModel.adapter
    }

}