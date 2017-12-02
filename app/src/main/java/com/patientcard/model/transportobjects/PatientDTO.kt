package com.patientcard.model.transportobjects

import java.io.Serializable

data class PatientDTO(val name: String,
                      val surname: String,
                      val patientcode: String) : Serializable