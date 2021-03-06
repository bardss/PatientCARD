package com.patientcard.logic.services

import android.widget.Toast
import com.google.gson.Gson
import com.patientcard.R
import com.patientcard.logic.database.Database
import com.patientcard.logic.model.businessobjects.ResponseErrorMessage
import com.patientcard.logic.model.transportobjects.*
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
import rx.functions.Func1
import rx.schedulers.Schedulers
import timber.log.Timber
import java.lang.Exception

object ServiceManager {

    fun loginDoctor(receiver: LoginDoctorReciever, login: String, password: String) {
        setupRequest(ServiceProvider
                .oauthApi
                .loginDoctor(login, password, "patientCard", "password"),
                Action1 { receiver.onLoginDoctorSuccess(it as TokenDTO) },
                Action1 {
                    handleError(it)
                    receiver.onLoginDoctorError(it)
                },
                Action0 { Timber.e("OnCompleted") })
    }

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

    fun editObservations(receiver: PutObservationReciever, observation: ObservationDTO) {
        setupRequest(ServiceProvider
                .observationsService
                .editObservation(observation),
                Action1 { receiver.onPutObservationSuccess(it as ObservationDTO) },
                Action1 {
                    handleError(it)
                    receiver.onPutObservationError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun editRecommendation(receiver: PutRecommendationReciever, recommendation: RecommendationDTO) {
        setupRequest(ServiceProvider
                .recommendationService
                .editRecommendation(recommendation),
                Action1 { receiver.onPutRecommendationSuccess(it as RecommendationDTO) },
                Action1 {
                    handleError(it)
                    receiver.onPutRecommendationError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun deleteRecommendation(receiver: DeleteRecommendationReciever, recommendationId: String) {
        setupRequest(ServiceProvider
                .recommendationService
                .deleteRecommendation(recommendationId),
                Action1 { receiver.onDeleteRecommendationSuccess() },
                Action1 {
                    handleError(it)
                    receiver.onDeleteRecommendationError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    fun deleteObservations(receiver: DeleteObservationReciever, observationId: String) {
        setupRequest(ServiceProvider
                .observationsService
                .deleteObservation(observationId),
                Action1 { receiver.onDeleteObservationSuccess() },
                Action1 {
                    handleError(it)
                    receiver.onDeleteObservationError()
                },
                Action0 { Timber.e("OnCompleted") })
    }

    private fun setupRequest(observable: Observable<*>, onNext: Action1<Any>, onError: Action1<Throwable>, onCompleted: Action0): Subscription {
        return observable
                .retryWhen(getRefreshNotificationHandler())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onCompleted)
    }

    private fun refreshToken(refreshToken: String?): Observable<*> {
        return ServiceProvider.oauthApi.refreshToken(refreshToken!!,"patientCard", "refresh_token")
                .subscribeOn(Schedulers.newThread())
                .doOnNext { token -> Database.putToken(token) }
    }

    private fun handleError(error: Throwable) {
        val msg = getResponseMessage(error)
        if (error is HttpException) {
            val context = ApplicationContext.appContext
            if (error.code() != 404 && error.code() != 401 && error.code() != 404) {
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

    private fun getRefreshNotificationHandler(): Func1<Observable<out Throwable>, Observable<Any>> {
        return Func1 { observable ->
            observable
                    .zipWith(Observable.range(1, 3)) { error, _ -> error }
                    .flatMap(Func1<Any, Observable<*>> { error ->
                        if (error is HttpException && error.code() == 401) {
                            val refreshToken = Database.getToken().refresh_token
                            return@Func1 refreshToken(refreshToken)
                        }
                        Observable.error<Any>(error as Throwable)
                    })
        }
    }

}