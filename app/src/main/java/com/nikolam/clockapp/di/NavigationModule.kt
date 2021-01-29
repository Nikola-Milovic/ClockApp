package com.nikolam.clockapp.di

import com.nikolam.common.navigation.NavManager
import org.koin.dsl.module

val navigationModule = module {
    single { NavManager() }
}
