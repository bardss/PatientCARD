package com.example.jakubaniola.patientcard.access

import android.os.Bundle
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.rsqtechnologies.rsqphysio.base.BaseActivity
import easymvp.annotation.ActivityView
import easymvp.annotation.Presenter

@ActivityView(layout = R.layout.activity_access, presenter = AccessPresenterImpl::class)
class AccessActivity : BaseActivity(), AccessView {

    @Presenter
    lateinit var presenter: AccessPresenter

    override fun providePresenter(): BasePresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access)
    }
}
