package med.hospital.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.hospital.api.domain.address.AddressInfo;

@Table(name = "doctors")
@Entity(name = "Doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private Specialty especialidad;
    @Embedded
    private AddressInfo direccion;

    public Doctor(DataToRegisterDoctor dataToRegisterDoctor) {
        this.nombre = dataToRegisterDoctor.nombre();
        this.email = dataToRegisterDoctor.email();
        this.telefono = dataToRegisterDoctor.telefono();
        this.active = true;
        this.documento = dataToRegisterDoctor.documento();
        this.especialidad = dataToRegisterDoctor.especialidad();
        this.direccion = new AddressInfo(dataToRegisterDoctor.direccion());
    }

    public void updateDataDoctor(DataToUpdateDoctor dataToUpdateDoctor) {
        if(dataToUpdateDoctor.nombre() != null) {
            this.nombre = dataToUpdateDoctor.nombre();
        }
        if (dataToUpdateDoctor.documento() != null) {
            this.documento = dataToUpdateDoctor.documento();
        }
        if (dataToUpdateDoctor.direccion() != null) {
            this.direccion = direccion.updateAddressInfo(dataToUpdateDoctor.direccion());
        }
    }

    public void deactivateDoctor() {
        this.active = false;
    }

    /*
    public Doctor(String nombre, String email, String documento, Specialty especialidad, DataAddress direccion) {
    }
     */
}
