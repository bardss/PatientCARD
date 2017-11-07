package com.patientcard.shortfever

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.patientcard.R
import kotlinx.android.synthetic.main.item_short_fever.view.*


internal class ShortFeverAdapter(private val shortFeverRecyclerView: RecyclerView) : RecyclerView.Adapter<ShortFeverAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_short_fever, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemLinearLayout.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, shortFeverRecyclerView.height/6)
        holder.dateTextView.text = (position + 1).toString()
        holder.temperatureTextView.text = (36 + position).toString()
        holder.pulseTextView.text = (120 + position).toString()
    }

    override fun getItemCount(): Int {
        return 6
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemLinearLayout = view.itemLinearLayout
        val dateTextView = view.itemDateTextView
        val pulseTextView = view.itemPulseTextView
        val temperatureTextView = view.itemTemperatureTextView
    }

}