package med.hospital.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.hospital.api.domain.address.DataAddress;

public record DataToUpdatePatient(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DataAddress direccion
) {
}
