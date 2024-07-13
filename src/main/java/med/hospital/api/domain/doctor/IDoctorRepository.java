package med.hospital.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IDoctorRepository extends JpaRepository <Doctor, Long>{
    Page<Doctor> findByActiveTrue(Pageable pagination);

    @Query("""
            select d from Doctor d
            where d.active = true
            and
            d.especialidad=:especialidad
            and
            d.id not in(
                select c.doctor.id from Consultation c
                where
                c.date=:date
            )
            order by rand()
            limit 1
            """)
    Doctor selectAvailableSpecialtyDoctorOnGivenDate(Specialty especialidad, LocalDateTime date);

    @Query("""
            select d.active
            from Doctor d
            where d.id=:idDoctor
            """)
    Boolean findActiveById(Long idDoctor);
}
























