package com.patientcard.views.base

import easymvp.RxPresenter

abstract class BaseAbstractPresenter<V : BaseView> : RxPresenter<V>(), BasePresenter {

//    abstract val model: BaseModel

//    val database: Database
//        get() = Database()
//
//    var token: Token
//        get() = database.getToken()
//        set(token) {
//            database.putToken(token)
//        }
}