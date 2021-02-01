package com.nikolam.feature_alarms.presentation

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.common.navigation.NavManager
import com.nikolam.data.db.AlarmDatabase
import com.nikolam.data.db.models.AlarmModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlarmsViewModel(
    private val navManager: NavManager,
    private val alarmDatabase: AlarmDatabase
) : ViewModel() {

         private val _alarmLiveData: MutableLiveData<ArrayList<AlarmModel>> = MutableLiveData()
        val alarmLiveData: LiveData<ArrayList<AlarmModel>>
            get() = _alarmLiveData


    fun navigateToNewAlarm() {
        val uri = Uri.parse("clock://addAlarm")
        navManager.navigate(uri)
    }

    fun getAlarms(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _alarmLiveData.postValue(ArrayList(alarmDatabase.alarmDao().getAllAlarms()))
            }
        }
    }

}