package med.hospital.api.domain.address;

import jakarta.validation.constraints.NotBlank;

public record DataAddress(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento
) {
}
