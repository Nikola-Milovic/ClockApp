package com.nikolam.feature_add_alarm.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolam.feature_add_alarm.R

class AddAlarmFragment : Fragment() {

    companion object {
        fun newInstance() = AddAlarmFragment()
    }

    private lateinit var viewModel: AddAlarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_alarm_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAlarmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}