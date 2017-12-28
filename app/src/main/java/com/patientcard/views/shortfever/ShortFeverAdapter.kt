package com.patientcard.views.shortfever

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import kotlinx.android.synthetic.main.item_short_fever.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


class ShortFeverAdapter(private val shortFeverRecyclerView: RecyclerView) : RecyclerView.Adapter<ShortFeverAdapter.ViewHolder>() {

    private var feverCard: List<FeverCardDTO>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_short_fever, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemLinearLayout.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, shortFeverRecyclerView.height/6)
        holder.dateTextView.text = getFormattedDate(feverCard?.get(position)?.date) + "\n" + feverCard?.get(position)?.timeOfDay?.stringValue
        holder.temperatureTextView.text = feverCard?.get(position)?.temperature?.toString()
        holder.pulseTextView.text = feverCard?.get(position)?.pulse?.toString()
    }

    override fun getItemCount(): Int {
        return if (feverCard == null || feverCard?.size == 0) 0 else 6
    }

    fun getFormattedDate(date: LocalDate?): String? {
        val dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return date?.format(dtf)
    }

    fun setFeverCard(feverCard: List<FeverCardDTO>) {
        this.feverCard = feverCard
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemLinearLayout = view.itemLinearLayout
        val dateTextView = view.itemDateTextView
        val pulseTextView = view.itemPulseTextView
        val temperatureTextView = view.itemTemperatureTextView
    }

}