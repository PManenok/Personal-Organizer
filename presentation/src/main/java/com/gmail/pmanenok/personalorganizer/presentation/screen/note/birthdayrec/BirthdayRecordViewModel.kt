package com.gmail.pmanenok.personalorganizer.presentation.screen.note.birthdayrec

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import com.gmail.pmanenok.domain.usecases.note.AddRecordUseCase
import com.gmail.pmanenok.domain.usecases.note.DeleteRecordUseCase
import com.gmail.pmanenok.domain.usecases.note.GetRecordByIdUseCase
import com.gmail.pmanenok.domain.usecases.note.UpdateRecordUseCase
import com.gmail.pmanenok.personalorganizer.R
import com.gmail.pmanenok.personalorganizer.app.App
import com.gmail.pmanenok.personalorganizer.presentation.base.BaseViewModel
import com.gmail.pmanenok.personalorganizer.presentation.screen.main.MainRouter
import com.gmail.pmanenok.personalorganizer.presentation.screen.note.NoteRouter
import java.util.*
import javax.inject.Inject

class BirthdayRecordViewModel : BaseViewModel<NoteRouter>() {
    @Inject
    public lateinit var getRecordByIdUseCase: GetRecordByIdUseCase
    @Inject
    public lateinit var addRecordUseCase: AddRecordUseCase
    @Inject
    public lateinit var updateRecordUseCase: UpdateRecordUseCase
    @Inject
    public lateinit var deleteRecordUseCase: DeleteRecordUseCase

    init {
        App.appComponent.inject(this)
    }

    val title = ObservableField<String>()
    val name = ObservableField<String>()
    val date = ObservableField<String>()
    val comment = ObservableField<String>()
    var day: Long = 0L

    //val notify = ObservableBoolean(false)
    val backOnClick = View.OnClickListener { router?.goBack() }
    val notifyOnClick = View.OnClickListener { /*router?.showNotify()*/ }
}