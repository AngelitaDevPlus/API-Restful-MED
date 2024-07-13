package med.hospital.api.domain.consultation.validations;
import jakarta.validation.ValidationException;
import med.hospital.api.domain.consultation.DataScheduleConsultation;
import med.hospital.api.domain.consultation.IConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutConsultation implements IValidatorConsultation{
    @Autowired
    private IConsultationRepository consultationRepository;
    public void validate(DataScheduleConsultation data) {
        var firstAppointment =  data.date().withHour(7);
        var lastAppointment = data.date().withHour(18);

        var patientWithConsultation = consultationRepository
                .existsByPatientIdAndDateBetween(data.idPatient(),
                        firstAppointment, lastAppointment);

        if(patientWithConsultation) {
            throw new ValidationException("Patient already has a " +
                    "schedule consultation for that day");
        }
    }
}
