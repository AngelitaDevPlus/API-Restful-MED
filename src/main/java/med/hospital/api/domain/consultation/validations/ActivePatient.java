package med.hospital.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.hospital.api.domain.consultation.DataScheduleConsultation;
import med.hospital.api.domain.patient.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatient implements IValidatorConsultation{
    @Autowired
    private IPatientRepository patientRepository;
    public void validate(DataScheduleConsultation data) {
        if(data.idPatient()==null) {
            return;
        }
        var activePatient = patientRepository.findActiveById(data.idPatient());

        if(!activePatient) {
            throw new ValidationException("Patient as inactive in the system," +
                    " can't schedule a consultation");
        }
    }
}
