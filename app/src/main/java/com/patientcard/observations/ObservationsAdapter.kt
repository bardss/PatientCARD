package com.patientcard.observations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patientcard.R
import com.patientcard.utils.ResUtil
import kotlinx.android.synthetic.main.item_observations.view.*


internal class ObservationsAdapter : RecyclerView.Adapter<ObservationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_observations, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personValueTextView.text = ResUtil.getString(R.string.person_placeholder) + " " + position
    }

    override fun getItemCount(): Int {
        return 5
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val personValueTextView = view.personValueTextView
    }

}