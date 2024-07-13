package med.hospital.api.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IConsultationRepository extends JpaRepository <Consultation, Long>{

    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstAppointment, LocalDateTime lastAppointment);
    Boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);
}
