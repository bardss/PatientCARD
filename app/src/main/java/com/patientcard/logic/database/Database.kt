package com.patientcard.logic.database

import com.patientcard.logic.model.transportobjects.TokenDTO
import io.paperdb.Paper

object Database {

    fun getToken(): TokenDTO {
        return Paper.book().read(DatabaseKeys.TOKEN.name)
    }

    fun putToken(token: TokenDTO) {
        Paper.book().write(DatabaseKeys.TOKEN.name, token)
    }

    fun deleteToken() {
        Paper.book().delete(DatabaseKeys.TOKEN.name)
    }

    fun getLogin(): String {
        return Paper.book().read(DatabaseKeys.LOGIN.name) ?: return ""
    }

    fun putLogin(login: String) {
        Paper.book().write(DatabaseKeys.LOGIN.name, login)
    }

    fun putDoctorName(): String {
        return Paper.book().read(DatabaseKeys.DOCTOR_NAME.name) ?: return ""
    }

    fun getDoctorName(doctorName: String) {
        Paper.book().write(DatabaseKeys.DOCTOR_NAME.name, doctorName)
    }
}