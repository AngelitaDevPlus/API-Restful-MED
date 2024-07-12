package med.hospital.api.domain.doctor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.hospital.api.domain.address.DataAddress;

public record DataToRegisterDoctor(
        @NotBlank //escogemos este porque también valida que no llegue @NotNull
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull
        Specialty especialidad,
        @NotNull // @NotNull si y @NotBlank no porque es un objeto (no va a llegar en blanco, va a llegar nulo)
        @Valid
        DataAddress direccion
) {
}
