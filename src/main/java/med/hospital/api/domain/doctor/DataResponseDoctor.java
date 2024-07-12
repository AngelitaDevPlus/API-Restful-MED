package med.hospital.api.domain.doctor;

import med.hospital.api.domain.address.DataAddress;

public record DataResponseDoctor(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DataAddress direccion
) {
}
