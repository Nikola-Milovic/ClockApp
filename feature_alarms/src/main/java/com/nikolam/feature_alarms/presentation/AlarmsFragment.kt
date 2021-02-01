package com.nikolam.feature_alarms.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolam.feature_alarms.R
import com.nikolam.feature_alarms.databinding.AlarmsFragmentBinding
import com.nikolam.feature_alarms.di.alarmsModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class AlarmsFragment : Fragment() {

    private val viewModel: AlarmsViewModel by inject()

    private var _binding: AlarmsFragmentBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlarmsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.newAlarmFloatingActionButton.setOnClickListener{
            viewModel.navigateToNewAlarm()
        }

        val adapter = AlarmsAdapter()
        binding.alarmsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.alarmsRecyclerView.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )
        binding.alarmsRecyclerView.adapter = adapter

        viewModel.alarmLiveData.observe(viewLifecycleOwner, Observer {
            adapter.newData(it)
        })

        viewModel.getAlarms()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(alarmsModule)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        unloadKoinModules(alarmsModule)
    }
}