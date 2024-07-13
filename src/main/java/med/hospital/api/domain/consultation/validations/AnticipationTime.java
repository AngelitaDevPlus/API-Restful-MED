package med.hospital.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.hospital.api.domain.consultation.DataScheduleConsultation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AnticipationTime implements IValidatorConsultation{
    public void validate(DataScheduleConsultation data) {
        var now = LocalDateTime.now();
        var consultationTime = data.date();

        var timeDifference = Duration.between(
                now, consultationTime).toMinutes() < 30;

        if(timeDifference) {
            throw new ValidationException("Consultations must " +
                    "be schedule at least 30 minutes before");
        }
    }
}
