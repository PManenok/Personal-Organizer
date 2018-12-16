package com.gmail.pmanenok.personalorganizer.presentation.screen.main

import android.support.v7.widget.PopupMenu
import android.util.Log
import android.view.View
import com.gmail.pmanenok.domain.entity.NoteType
import com.gmail.pmanenok.domain.usecases.date.GetSelectedDayUseCase
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainViewModel : BaseViewModel<MainRouter>() {
    var selectedDay: Long = 0L

    val newNoteOnClick = View.OnClickListener {
        router?.goToNoteActivity(NoteType.TYPE_NOTE, date = selectedDay)
    }
    val searchOnClick = View.OnClickListener {
        router?.goToSearchActivity()
    }
    val settingsOnClick = View.OnClickListener {
        router?.goToSettingsActivity()
    }
    val newNoteMenuListener = PopupMenu.OnMenuItemClickListener {
        when (it.itemId) {
            R.id.new_note -> {
                router?.goToNoteActivity(NoteType.TYPE_NOTE, date = selectedDay)
                true
            }
            R.id.new_birthday -> {
                router?.goToNoteActivity(NoteType.TYPE_BIRTHDAY, date = selectedDay)
                true
            }
            R.id.new_list -> {
                router?.goToNoteActivity(NoteType.TYPE_LIST, date = selectedDay)
                true
            }
            else -> {
                false
            }
        }
    }
    @Inject
    public lateinit var getSelectedDayUseCase: GetSelectedDayUseCase

    init {
        App.appComponent.inject(this)
        addToDisposable(getSelectedDayUseCase.get().subscribeBy(
            onNext = {
                Log.e("MainViewModel", "getSelectedDayUseCase selectedDay $it")
                selectedDay = it
            },
            onError = {
                router?.showError(it)
            }
        ))
    }
}