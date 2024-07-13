package med.hospital.api.domain.consultation.validations;
import jakarta.validation.ValidationException;
import med.hospital.api.domain.consultation.DataScheduleConsultation;
import med.hospital.api.domain.consultation.IConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithConsultation implements IValidatorConsultation{
    @Autowired
    private IConsultationRepository consultationRepository;

    public void validate(DataScheduleConsultation data) {
        if(data.idDoctor()==null) {
            return;
        }

        var doctorWithAppointment = consultationRepository
                .existsByDoctorIdAndDate(data.idDoctor(), data.date());

        if(doctorWithAppointment) {
            throw new ValidationException("This doctor has already " +
                    "a schedule consultation at that time");
        }
    }
}
