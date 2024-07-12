package med.hospital.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.hospital.api.domain.address.AddressInfo;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    private String telefono;

    private String documento;

    @Embedded
    private AddressInfo direccion;

    private Boolean active;

    public Patient(DataToRegisterPatient datos) {
        this.active = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.direccion = new AddressInfo(datos.direccion());
    }

    public void updateDataPatient(DataToUpdatePatient datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.telefono() != null) {
            this.telefono = datos.telefono();
        }
        if (datos.direccion() != null) {
            this.direccion.updateAddressInfo(datos.direccion());
        }
    }

    public void deactivatePatient() {
        this.active = false;
    }
}
