package med.hospital.api.domain.patient;

public record DataToListPatient(
        Long id,
        String nombre,
        String email,
        String documento
) {
    public DataToListPatient(Patient patient) {
        this(
                patient.getId(),
                patient.getNombre(),
                patient.getEmail(),
                patient.getDocumento()
        );
    }
}
