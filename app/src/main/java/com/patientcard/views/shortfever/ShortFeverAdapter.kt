package com.patientcard.views.shortfever

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.logic.model.transportobjects.TimeOfDay
import com.patientcard.logic.utils.FormatTimeDateUtil
import kotlinx.android.synthetic.main.item_short_fever.view.*
import java.util.*


class ShortFeverAdapter(private val recyclerViewHeight: Int) : RecyclerView.Adapter<ShortFeverAdapter.ViewHolder>() {

    private var feverCard: List<FeverCardDTO>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_short_fever, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemLinearLayout.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, recyclerViewHeight/6)
        holder.dateTextView.text = FormatTimeDateUtil.getFormattedDate(getListItem(position)?.date) + "\n" + getListItem(position)?.timeOfDay?.stringValue
        holder.temperatureTextView.text = feverCard?.get(position)?.temperature?.toString()
        holder.pulseTextView.text = feverCard?.get(position)?.pulse?.toString()
    }

    fun getListItem(position: Int): FeverCardDTO? {
        return feverCard?.get(position)
    }

    override fun getItemCount(): Int {
        if (feverCard == null) {
            return 0
        } else if (feverCard?.size!! > 6) {
            return 6
        } else {
            return feverCard?.size!!
        }
    }

    fun setFeverCard(feverCard: List<FeverCardDTO>) {
        Collections.sort(feverCard) { feverCard1, feverCard2 ->
            if (feverCard1.date?.isEqual(feverCard2.date)!!){
                if (feverCard1.timeOfDay == TimeOfDay.EVENING) {
                    -1
                } else {
                    1
                }
            } else if (feverCard1.date!! > feverCard2.date!!){
                -1
            } else {
                1
            }
        }
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