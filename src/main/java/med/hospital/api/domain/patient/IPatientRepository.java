package med.hospital.api.domain.patient;

import med.hospital.api.domain.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable pagination);

    @Query("""
            select p.active
            from Patient p
            where p.id=:idPatient
            """)
    Boolean findActiveById(Long idPatient);
}
