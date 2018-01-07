
import com.patientcard.logic.model.transportobjects.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

object MockData {

    fun getQRCode(): String{
        return "122075"
    }

    fun getPatient(): PatientDTO? {
        return PatientDTO(1, "Test", "Testowy", "51022802413", "122075")
    }

    fun getObservations(): ArrayList<ObservationDTO> {
        val observations = ArrayList<ObservationDTO>()
        observations.add(ObservationDTO(1, 122075, "Jan Kowalski", LocalDateTime.now(), "Notatka 1"))
        observations.add(ObservationDTO(2, 122075, "Marian Kowalski", LocalDateTime.now().plusDays(1), "Notatka 2"))
        observations.add(ObservationDTO(3, 122075, "Janusz Kowalski", LocalDateTime.now().plusDays(2), "Notatka 3"))
        observations.add(ObservationDTO(4, 122075, "Jarosław Kowalski", LocalDateTime.now().plusDays(3), "Notatka 4"))
        return observations
    }

    fun getRecommendations(): ArrayList<RecommendationDTO> {
        val recommendations = ArrayList<RecommendationDTO>()
        recommendations.add(RecommendationDTO(1, 1, LocalDate.now(), "Notatka 1", LocalTime.parse("08:00"), LocalTime.parse("12:00"), LocalTime.parse("18:00"), LocalTime.parse("22:00")))
        recommendations.add(RecommendationDTO(2, 1, LocalDate.now(), "Notatka 2", LocalTime.parse("09:00"), LocalTime.parse("13:00"), LocalTime.parse("19:00"), null))
        return recommendations
    }

    fun getFeverCardList(): ArrayList<FeverCardDTO> {
        val feverCardList = ArrayList<FeverCardDTO>()
        feverCardList.add(FeverCardDTO(1, 1, LocalDate.now(), TimeOfDay.MORNING, 120, 36))
        feverCardList.add(FeverCardDTO(2, 1, LocalDate.now(), TimeOfDay.EVENING, 121, 37))
        feverCardList.add(FeverCardDTO(3, 1, LocalDate.now().plusDays(1), TimeOfDay.MORNING, 122, 38))
        feverCardList.add(FeverCardDTO(4, 1, LocalDate.now().plusDays(1), TimeOfDay.EVENING, 123, 39))
        feverCardList.add(FeverCardDTO(5, 1, LocalDate.now().plusDays(2), TimeOfDay.MORNING, 124, 40))
        feverCardList.add(FeverCardDTO(6, 1, LocalDate.now().plusDays(2), TimeOfDay.EVENING, 125, 41))
        return feverCardList
    }
}