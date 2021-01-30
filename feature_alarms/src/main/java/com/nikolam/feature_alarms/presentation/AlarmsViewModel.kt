package com.nikolam.feature_alarms.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.nikolam.common.navigation.NavManager

class AlarmsViewModel(
    private val navManager: NavManager
) : ViewModel() {
    //     private val _profileLiveData: MutableLiveData<ProfileModel> = MutableLiveData()
    //    val profileLiveData: LiveData<ProfileModel>
    //        get() = _profileLiveData


    fun navigateToNewAlarm() {
        val uri = Uri.parse("clock://addAlarm")
        navManager.navigate(uri)
    }

}