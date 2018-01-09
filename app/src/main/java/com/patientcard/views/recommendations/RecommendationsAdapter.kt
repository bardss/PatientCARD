package com.patientcard.views.recommendations

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.logic.utils.FormatTimeDateUtil
import com.patientcard.logic.utils.ResUtil
import kotlinx.android.synthetic.main.item_recommendations.view.*
import java.util.*


class RecommendationsAdapter(var context: Context?) : RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {

    var recommendationsList: List<RecommendationDTO>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recommendations, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recommendationDateTextView.text = ResUtil.getString(R.string.recommendation) + " " + FormatTimeDateUtil.getFormattedDate(recommendationsList?.get(position)?.date)
        holder.drugTextView.text = recommendationsList?.get(position)?.description
        holder.morningTimeTextView.text = FormatTimeDateUtil.getFormattedTime(recommendationsList?.get(position)?.morning)
        holder.noonTimeTextView.text = FormatTimeDateUtil.getFormattedTime(recommendationsList?.get(position)?.noon)
        holder.eveningTimeTextView.text = FormatTimeDateUtil.getFormattedTime(recommendationsList?.get(position)?.evening)
        holder.nightTimeTextView.text = FormatTimeDateUtil.getFormattedTime(recommendationsList?.get(position)?.night)
        holder.editImageView.setOnClickListener {
            if (context != null && context is RecommendationsView) {
                (context as RecommendationsView).clickEditRecommendation(getListItem(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return if (recommendationsList == null) 0 else recommendationsList?.size!!
    }

    fun getListItem(position: Int): RecommendationDTO? {
        return recommendationsList?.get(position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recommendationDateTextView = view.recommendationDateTextView
        val drugTextView = view.drugValueTextView
        val morningTimeTextView = view.morningTimeTextView
        val noonTimeTextView = view.noonTimeTextView
        val eveningTimeTextView = view.eveningTimeTextView
        val nightTimeTextView = view.nightTimeTextView
        val editImageView = view.editImageView
    }

    fun setRecommendations(recommendations: List<RecommendationDTO>) {
        Collections.sort<RecommendationDTO>(recommendations) { lhs, rhs -> rhs.date!!.compareTo(lhs.date) }
        recommendationsList = recommendations
        notifyDataSetChanged()
    }

}