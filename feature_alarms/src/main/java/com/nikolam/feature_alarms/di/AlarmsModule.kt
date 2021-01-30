package com.nikolam.feature_alarms.di

import com.nikolam.feature_alarms.presentation.AlarmsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val alarmsModule = module{
    viewModel { AlarmsViewModel(get()) }
}