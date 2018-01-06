package com.patientcard.views.recommendations

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.logic.utils.DataTimeFormatUtil
import com.patientcard.logic.utils.ResUtil
import kotlinx.android.synthetic.main.item_recommendations.view.*
import java.util.*


class RecommendationsAdapter : RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {

    var recommendationsList: List<RecommendationDTO>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recommendations, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recommendationDateTextView.text = ResUtil.getString(R.string.recommendation) + " " + DataTimeFormatUtil.getFormattedDate(recommendationsList?.get(position)?.date)
        holder.drugTextView.text = recommendationsList?.get(position)?.description
        holder.morningTimeTextView.text = DataTimeFormatUtil.getFormattedTime(recommendationsList?.get(position)?.morning)
        holder.noonTimeTextView.text = DataTimeFormatUtil.getFormattedTime(recommendationsList?.get(position)?.noon)
        holder.eveningTimeTextView.text = DataTimeFormatUtil.getFormattedTime(recommendationsList?.get(position)?.evening)
        holder.nightTimeTextView.text = DataTimeFormatUtil.getFormattedTime(recommendationsList?.get(position)?.night)
    }

    override fun getItemCount(): Int {
        return if (recommendationsList == null) 0 else recommendationsList?.size!!
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recommendationDateTextView = view.recommendationDateTextView
        val drugTextView = view.drugValueTextView
        val morningTimeTextView = view.morningTimeTextView
        val noonTimeTextView = view.noonTimeTextView
        val eveningTimeTextView = view.eveningTimeTextView
        val nightTimeTextView = view.nightTimeTextView
    }

    fun setRecommendations(recommendations: List<RecommendationDTO>) {
        Collections.sort<RecommendationDTO>(recommendations) { lhs, rhs -> rhs.date!!.compareTo(lhs.date) }
        recommendationsList = recommendations
        notifyDataSetChanged()
    }

}