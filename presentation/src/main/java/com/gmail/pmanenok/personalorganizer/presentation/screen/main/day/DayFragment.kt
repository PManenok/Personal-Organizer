package com.gmail.pmanenok.personalorganizer.presentation.screen.main.day

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.databinding.FragmentDayBinding
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseMvvmFragment
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter

class DayFragment : BaseMvvmFragment<DayViewModel, MainRouter, FragmentDayBinding>() {
    companion object {
        private const val DAY_EXTRA = "DAY_EXTRA"
        fun getInstance(id: Long): DayFragment {
            val fragment = DayFragment()
            val bundle = Bundle()
            bundle.putLong(DAY_EXTRA, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun prodiveViewModel(): DayViewModel {
        return ViewModelProviders.of(this).get(DayViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_day

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listRecyclerView.adapter = viewModel.adapter
        binding.listRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}