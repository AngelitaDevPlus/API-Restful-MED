package med.hospital.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.hospital.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record DataScheduleConsultation(
        Long id,
        @NotNull
        Long idPatient,
        Long idDoctor,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty especialidad
) {
}
