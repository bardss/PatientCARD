package com.rsqtechnologies.rsqphysio.base

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jakubaniola.patientcard.R
import com.example.jakubaniola.patientcard.base.BasePresenter
import com.example.jakubaniola.patientcard.base.BaseView

abstract class BaseFragment : Fragment(), BaseView {

    private var progress: ProgressDialog? = null

    protected val isNetworkAvailable: Boolean
        @SuppressLint("MissingPermission")
        get() {
            val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    override val activityContext: Context
        get() = context

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return TextView(activity)
    }

    override fun onResume() {
        super.onResume()
        (activity as BaseActivity).initExtras(providePresenter())
    }

    abstract fun providePresenter(): BasePresenter

    override fun startProgressDialog(message: String) {
        if ((progress == null || !progress!!.isShowing) && !activity.isFinishing) {
            progress = ProgressDialog.show(activity, getString(R.string.progress_loading_text),
                    message, true)
            Handler().postDelayed({ stopProgressDialog() }, 10000)
        }
    }

    override fun stopProgressDialog() {
        if (progress != null && activity != null && !activity.isFinishing && progress!!.isShowing) {
            progress!!.cancel()
        }
    }

    fun openActivity(classTo: Class<*>) {
        (activity as BaseActivity).openActivity(classTo)
    }


    fun performOnBackPressed() {
        activity.onBackPressed()
    }

}// Required empty public constructor