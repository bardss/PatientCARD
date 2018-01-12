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

}