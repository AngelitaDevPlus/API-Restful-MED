package med.hospital.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.hospital.api.domain.consultation.DataScheduleConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class BusinessHours implements IValidatorConsultation{
    public void validate(DataScheduleConsultation data) {
        var sunday = DayOfWeek.SUNDAY.equals(data.date().getDayOfWeek());

        var beforeOpening = data.date().getHour()<7;
        var afterClosing = data.date().getHour()>19;

        if(sunday || beforeOpening || afterClosing) {
            throw new ValidationException("Hospital Business Hours " +
                    "are Monday to Saturday from 07.00 to 19.00 hours");
        }


    }
}
