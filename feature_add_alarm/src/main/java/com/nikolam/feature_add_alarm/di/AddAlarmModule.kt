package com.nikolam.feature_add_alarm.di

import com.nikolam.feature_add_alarm.presentation.AddAlarmViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addAlarmModule = module {
    viewModel { AddAlarmViewModel() }
}