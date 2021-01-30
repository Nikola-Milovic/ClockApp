package com.nikolam.feature_add_alarm.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolam.feature_add_alarm.R
import com.nikolam.feature_add_alarm.databinding.AddAlarmFragmentBinding
import com.nikolam.feature_add_alarm.di.addAlarmModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AddAlarmFragment : Fragment() {

    private val viewModel: AddAlarmViewModel by inject()

    private var _binding: AddAlarmFragmentBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddAlarmFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.scheduleButton.setOnClickListener {
            viewModel.setAlarm(requireContext())
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(addAlarmModule)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(addAlarmModule)
    }
}