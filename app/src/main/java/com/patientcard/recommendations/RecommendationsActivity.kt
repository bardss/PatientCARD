package com.patientcard.recommendations

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.patientcard.R
import com.patientcard.addrecommendation.AddRecommendationActivity
import com.patientcard.base.BaseActivity
import com.patientcard.base.BasePresenter
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_recommendations.*

@ActivityView(layout = R.layout.activity_recommendations, presenter = RecommendationsPresenterImpl::class)
class RecommendationsActivity : BaseActivity(), RecomendationsView {

    @Presenter
    lateinit var presenter: RecommendationsPresenter

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
        recommendationsRecyclerView.adapter = RecommendationsAdapter()
    }

}
