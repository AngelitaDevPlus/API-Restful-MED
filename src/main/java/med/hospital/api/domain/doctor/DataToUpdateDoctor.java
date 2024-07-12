package med.hospital.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.hospital.api.domain.address.DataAddress;

public record DataToUpdateDoctor(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DataAddress direccion
) {
}
