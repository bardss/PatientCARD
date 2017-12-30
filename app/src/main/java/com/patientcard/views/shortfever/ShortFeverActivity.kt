package com.patientcard.views.shortfever

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.IntentKeys
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.views.addfevercard.AddFeverCardActivity
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.feverchart.FeverChartActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_short_fever.*



@ActivityView(layout = R.layout.activity_short_fever, presenter = ShortFeverPresenterImpl::class)
class ShortFeverActivity : BaseActivity(), ShortFeverView {

    @Presenter
    lateinit var presenter: ShortFeverPresenter

    var feverCardAdapter: ShortFeverAdapter? = null

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onStart() {
        super.onStart()
        setupShortFeverList()
    }

    override fun setupButtons(feverCard: ArrayList<FeverCardDTO>, patientName: String?, patientId: String?) {
        graphFrameLayout.setOnClickListener {
            startActivity(Intent(this, FeverChartActivity::class.java)
                    .putExtra(IntentKeys.FEVER_CARD, feverCard))
        }
        addFab.setOnClickListener {
            startActivity(Intent(this, AddFeverCardActivity::class.java)
                    .putExtra(IntentKeys.PATIENT_ID, patientId)
                    .putExtra(IntentKeys.PATIENT_NAME, patientName))
        }
    }

    private fun setupShortFeverList() {
        shortFeverRecyclerView.layoutManager = LinearLayoutManager(this)
        feverCardAdapter = ShortFeverAdapter(shortFeverRecyclerView)
        shortFeverRecyclerView.adapter = feverCardAdapter
        shortFeverRecyclerView.setOnTouchListener { _, _ -> true }
    }

    override fun setFeverCard(feverCard: List<FeverCardDTO>) {
        feverCardAdapter?.setFeverCard(feverCard)
    }

    override fun setPatientName(patientName: String?) {
        nameTextView.text = patientName
    }

}