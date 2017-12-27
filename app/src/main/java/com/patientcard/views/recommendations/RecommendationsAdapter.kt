package com.patientcard.views.recommendations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patientcard.R
import com.patientcard.logic.utils.ResUtil
import kotlinx.android.synthetic.main.item_recommendations.view.*


internal class RecommendationsAdapter : RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recommendations, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.drugTextView.text = ResUtil.getString(R.string.drug_placeholder) + " " + position
        holder.morningTimeTextView.text = "0"+ position + ":00"
        holder.noonTimeTextView.text = "--"
        holder.eveningTimeTextView.text = "--"
        holder.nightTimeTextView.text = "0"+ position + ":00"
    }

    override fun getItemCount(): Int {
        return 5
    }

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val drugTextView = view.drugValueTextView
        val morningTimeTextView = view.morningTimeTextView
        val noonTimeTextView = view.noonTimeTextView
        val eveningTimeTextView = view.eveningTimeTextView
        val nightTimeTextView = view.nightTimeTextView
    }

}