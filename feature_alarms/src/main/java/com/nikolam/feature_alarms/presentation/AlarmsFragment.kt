package com.nikolam.feature_alarms.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolam.feature_alarms.R

class AlarmsFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmsFragment()
    }

    private lateinit var viewModel: AlarmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alarms_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlarmsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}