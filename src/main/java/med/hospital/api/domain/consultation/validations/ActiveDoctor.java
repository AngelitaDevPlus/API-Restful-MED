package med.hospital.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.hospital.api.domain.consultation.DataScheduleConsultation;
import med.hospital.api.domain.doctor.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
//@Component
public class ActiveDoctor implements IValidatorConsultation{
    @Autowired
    private IDoctorRepository doctorRepository;
    public void validate(DataScheduleConsultation data) {
        if(data.idDoctor()==null) {
            return;
        }
        var activeDoctor = doctorRepository.findActiveById(data.idDoctor());
        //System.out.println("data.idDoctor() " + data.idDoctor());
        //System.out.println("activeDoctor " + activeDoctor);

        if(!activeDoctor) {
            throw new ValidationException("Doctor is inactive in the system," +
                    " can't schedule a consultation");
        }
    }
}
















