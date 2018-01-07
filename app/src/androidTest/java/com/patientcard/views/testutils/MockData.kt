import com.patientcard.logic.model.transportobjects.PatientDTO

object MockData {

    fun getQRCode(): String{
        return "122075"
    }

    fun getPatient(): PatientDTO? {
        return PatientDTO(1, "Test", "Testowy", "51022802413", "122075")
    }

}