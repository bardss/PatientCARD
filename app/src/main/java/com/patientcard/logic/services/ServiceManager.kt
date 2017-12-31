package com.patientcard.logic.services

import android.widget.Toast
import com.google.gson.Gson
import com.patientcard.R
import com.patientcard.logic.model.businessobjects.ResponseErrorMessage
import com.patientcard.logic.model.transportobjects.FeverCardDTO
import com.patientcard.logic.model.transportobjects.ObservationDTO
import com.patientcard.logic.model.transportobjects.PatientDTO
import com.patientcard.logic.model.transportobjects.RecommendationDTO
import com.patientcard.logic.services.receivers.*
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

    fun getObservations(receiver: GetObservationsReciever, patientId: String) {
        setupRequest(ServiceProvider
                .observationsService
                .getObservations(patientId),
                Action1 { receiver.onGetObservationsSuccess(it as List<ObservationDTO>) },
                Action1 {
                    handleError(it)
                    receiver.onGetObservationError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun getRecommendations(receiver: GetRecommendationsReciever, patientId: String) {
        setupRequest(ServiceProvider
                .recommendationService
                .getRecommendations(patientId),
                Action1 { receiver.onGetRecommendationsSuccess(it as List<RecommendationDTO>) },
                Action1 {
                    handleError(it)
                    receiver.onGetRecommendationsError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun getFeverCard(receiver: GetFeverCardReciever, patientId: String) {
        setupRequest(ServiceProvider
                .feverCardService
                .getFeverCard(patientId),
                Action1 { receiver.onGetFeverCardSuccess(it as List<FeverCardDTO>) },
                Action1 {
                    handleError(it)
                    receiver.onGetFeverCardError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun addObservations(receiver: PostObservationReciever, observation: ObservationDTO) {
        setupRequest(ServiceProvider
                .observationsService
                .addObservation(observation),
                Action1 { receiver.onPostObservationSuccess(it as ObservationDTO) },
                Action1 {
                    handleError(it)
                    receiver.onPostObservationError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun addRecommendation(receiver: PostRecommendationReciever, recommendation: RecommendationDTO) {
        setupRequest(ServiceProvider
                .recommendationService
                .addRecommendation(recommendation),
                Action1 { receiver.onPostRecommendationSuccess(it as RecommendationDTO) },
                Action1 {
                    handleError(it)
                    receiver.onPostRecommendationError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun addFeverCard(receiver: PostFeverCardReciever, feverCard: FeverCardDTO) {
        setupRequest(ServiceProvider
                .feverCardService
                .addFeverCard(feverCard),
                Action1 { receiver.onPostFeverCardSuccess(it as FeverCardDTO) },
                Action1 {
                    handleError(it)
                    receiver.onPostFeverCardError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    private fun setupRequest(observable: Observable<*>, onNext: Action1<Any>, onError: Action1<Throwable>, onCompleted: Action0): Subscription {
        return observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onCompleted)
    }

    private fun handleError(error: Throwable) {
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