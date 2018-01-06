package com.patientcard.views.observations

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.logic.utils.FormatTimeDateUtil
import com.patientcard.logic.utils.ResUtil
import kotlinx.android.synthetic.main.item_observations.view.*
import java.util.*


class ObservationsAdapter(val context: Context) : RecyclerView.Adapter<ObservationsAdapter.ViewHolder>() {

    var observationsList: List<ObservationDTO>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_observations, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personValueTextView.text = observationsList?.get(position)?.employee
        holder.noteValueTextView.text = observationsList?.get(position)?.note
        if (observationsList?.get(position)?.dateTime != null) {
            holder.observationDateTextView.text = ResUtil.getString(R.string.observation) + " " + FormatTimeDateUtil.getFormattedDateTime(observationsList?.get(position)?.dateTime!!)
        }
        holder.editImageView.setOnClickListener {
            (context as ObservationsView).clickEditObservation(observationsList?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return if (observationsList == null) 0 else observationsList?.size!!
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val personValueTextView = view.personValueTextView
        val observationDateTextView = view.observationDateTextView
        val noteValueTextView = view.noteValueTextView
        val editImageView = view.editImageView
    }

    fun setObservations(observations: List<ObservationDTO>) {
        Collections.sort<ObservationDTO>(observations) { lhs, rhs -> rhs.dateTime!!.compareTo(lhs.dateTime) }
        observationsList = observations
        notifyDataSetChanged()
    }

}