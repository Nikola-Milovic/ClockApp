package com.nikolam.feature_alarms.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.data.db.models.AlarmModel
import com.nikolam.feature_alarms.databinding.AlarmItemBinding
import java.util.*
import kotlin.collections.ArrayList

class AlarmsAdapter(private val listener: AlarmItemListener) :
        RecyclerView.Adapter<AlarmsAdapter.AlarmViewHolder>() {

    override fun onBindViewHolder(holder: AlarmsAdapter.AlarmViewHolder, position: Int) {
        val data = data[position]
        try {
            holder.bind(data, listener)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmsAdapter.AlarmViewHolder {
        val itemBinding =
                AlarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlarmViewHolder(itemBinding)
    }

    override fun getItemCount() = data.size

    private val data = ArrayList<AlarmModel>()

    fun newData(newData: List<AlarmModel>) {
        if (data == newData)
            return
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class AlarmViewHolder(
            private val itemBinding: AlarmItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: AlarmModel, listener: AlarmItemListener) {
            itemBinding.apply {
                timeTextView.text = Date(data.id).toString()
                deleteButton.setOnClickListener {
                    listener.deleteAlarm(data.id)
                }
            }
        }
    }
}
