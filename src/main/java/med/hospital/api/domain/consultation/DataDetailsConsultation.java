package med.hospital.api.domain.consultation;

import java.time.LocalDateTime;

public record DataDetailsConsultation(
        Long id,
        Long idPatient,
        Long idDoctor,
        LocalDateTime date
) {
    public DataDetailsConsultation(Consultation consultation) {
        this(
                consultation.getId(),
                consultation.getPatient().getId(),
                consultation.getDoctor().getId(),
                consultation.getDate()
        );
    }
}
