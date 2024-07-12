package med.hospital.api.domain.patient;

import med.hospital.api.domain.address.AddressInfo;
import med.hospital.api.domain.address.DataAddress;

public record DataDetailsPatient(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        AddressInfo direccion
) {
    public DataDetailsPatient(Patient patient) {
        this(
                patient.getId(),
                patient.getNombre(),
                patient.getEmail(),
                patient.getDocumento(),
                patient.getTelefono(),
                patient.getDireccion()
        );
    }
}
