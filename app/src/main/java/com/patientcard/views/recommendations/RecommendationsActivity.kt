package com.patientcard.views.recommendations

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.patientcard.R
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.views.addrecommendation.AddRecommendationActivity
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_recommendations.*

@ActivityView(layout = R.layout.activity_recommendations, presenter = RecommendationsPresenterImpl::class)
class RecommendationsActivity : BaseActivity(), RecommendationsView {

    @Presenter
    lateinit var presenter: RecommendationsPresenter

    var recommendationsAdapter: RecommendationsAdapter? = null

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupRecommendationsList()
        setupButtoms()
    }

    private fun setupButtoms() {
        addFab.setOnClickListener {
            startActivity(Intent(this, AddRecommendationActivity::class.java))
        }
    }

    private fun setupRecommendationsList() {
        recommendationsRecyclerView.layoutManager = LinearLayoutManager(this)
        recommendationsAdapter = RecommendationsAdapter()
        recommendationsRecyclerView.adapter = recommendationsAdapter
    }

    override fun setRecommendationsList(recommendations: List<RecommendationDTO>) {
        recommendationsAdapter?.setRecommendations(recommendations)
    }

}
