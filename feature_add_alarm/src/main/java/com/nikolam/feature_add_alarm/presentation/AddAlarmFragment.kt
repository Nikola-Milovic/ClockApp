package com.nikolam.feature_add_alarm.presentation

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nikolam.feature_add_alarm.databinding.AddAlarmFragmentBinding
import com.nikolam.feature_add_alarm.di.addAlarmModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import java.util.*


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
            val datePicker = binding.datePicker
            val timePicker = binding.timePicker

            val calendar: Calendar = GregorianCalendar(datePicker.year,
                    datePicker.month,
                    datePicker.dayOfMonth,
                    timePicker.hour,
                    timePicker.minute)

            val time = calendar.timeInMillis

            val date =  DateUtils.getRelativeDateTimeString(context, time,
                    DateUtils.MINUTE_IN_MILLIS, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_NO_YEAR)
            Toast.makeText(context, date, Toast.LENGTH_SHORT).show()
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