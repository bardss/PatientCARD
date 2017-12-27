package com.patientcard.logic.services

import android.widget.Toast
import com.google.gson.Gson
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.ResponseErrorMessage
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.logic.services.receivers.GetPatientReciever
import com.patientcard.logic.utils.ResUtil
import com.patientcard.logic.utils.ToastUtil
import com.patientcard.views.base.ApplicationContext
import retrofit2.HttpException
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers
import timber.log.Timber
import java.lang.Exception

object ServiceManager {

    fun getPatient(receiver: GetPatientReciever, qrCode: String) {
        setupRequest(ServiceProvider
                .patientService
                .getPatient(qrCode),
                Action1 { receiver.onGetPatientSuccess(it as PatientDTO) },
                Action1 {
                    handleError(it)
                    receiver.onGetPatientError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    private fun setupRequest(observable: Observable<*>, onNext: Action1<Any>, onError: Action1<Throwable>, onCompleted: Action0): Subscription {
        return observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onCompleted)
    }

    fun handleError(error: Throwable) {
        val msg = getResponseMessage(error)

        if (error is HttpException) {
            val context = ApplicationContext.appContext

            if (error.code() != 401 && error.code() != 404) {
                if (error.code() == 409 && msg != null && !msg.isEmpty()) {
                    ToastUtil.show(context, msg, Toast.LENGTH_LONG)
                } else {
                    ToastUtil.show(context, ResUtil.getString(R.string.sth_wrong_check_internet_connection), Toast.LENGTH_LONG)
                }
            } else if (error.code() == 401) {
                ToastUtil.show(context, ResUtil.getString(R.string.authorization_error), Toast.LENGTH_LONG)
            }
        }
    }

    private fun getResponseMessage(error: Throwable): String? {
        var msg = ""
        if (error is HttpException && error.response() != null && error.response().errorBody() != null) {
            try {
                val response = error.response()
                msg = response.errorBody()?.string() ?: ""
                msg = Gson().fromJson(msg, ResponseErrorMessage::class.java).message
            } catch (e: Exception) {
                Timber.e("unable to get error message in ServiceManager::handleError()")
            }

        }
        return msg
    }


}