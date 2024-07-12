package med.hospital.api.domain.doctor;

public record DataToListDoctor(
        Long id,
        String nombre,
        Specialty especialidad,
        String documento,
        String email
) {
    public DataToListDoctor(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getNombre(),
                doctor.getEspecialidad(),
                doctor.getDocumento(),
                doctor.getEmail()
        );
    }
}
