package com.patientcard.views.addfevercard

import android.content.Intent
import com.patientcard.R
import com.patientcard.views.base.BaseActivity
import com.patientcard.views.base.BasePresenter
import com.patientcard.views.shortfever.ShortFeverActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter
import kotlinx.android.synthetic.main.activity_add_fevercard.*

@ActivityView(layout = R.layout.activity_add_fevercard, presenter = AddFeverCardPresenterImpl::class)
class AddFeverCardActivity : BaseActivity(), AddFeverCardView {

    @Presenter
    lateinit var presenter: AddFeverCardPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun setupButton() {
        checkFab.setOnClickListener {
            startActivity(Intent(this, ShortFeverActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
        }
    }

    override fun setPatientName(patientName: String?) {
        nameTextView.text = patientName
    }
}