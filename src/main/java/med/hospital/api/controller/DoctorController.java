package med.hospital.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.hospital.api.domain.address.DataAddress;
import med.hospital.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired //para que Spring inyecte, ubique la interfaz
    private IDoctorRepository doctorRepository;
    //No recomendable porque dificulta hacer pruebas unitarias, no se puede hacer un mock del atributo que es @Autowired

    @PostMapping
    @Transactional //No necesita porque interactua directamente con el repository.save()
    public ResponseEntity<DataResponseDoctor> registerDoctor(@RequestBody @Valid DataToRegisterDoctor dataToRegisterDoctor,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        Doctor doctor = doctorRepository.save(new Doctor(dataToRegisterDoctor));
        // Must return 201 Created
        // Because REST specification: URL where to find the doctor created
        DataResponseDoctor dataResponseDoctor = new DataResponseDoctor(doctor.getId(), doctor.getNombre(), doctor.getEmail(),
                doctor.getTelefono(), doctor.getEspecialidad().toString(),
                new DataAddress(doctor.getDireccion().getCalle(), doctor.getDireccion().getDistrito(),
                        doctor.getDireccion().getCiudad(), doctor.getDireccion().getNumero(),
                        doctor.getDireccion().getComplemento()));
        URI url = uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(dataResponseDoctor);
        /*
        doctorRepository.save(new Doctor(dataToRegisterDoctor.nombre(), dataToRegisterDoctor.email(),
                dataToRegisterDoctor.documento(), dataToRegisterDoctor.especialidad(),
                dataToRegisterDoctor.direccion()));
         */
    }

    @GetMapping()
    public ResponseEntity<Page<DataToListDoctor>> listingDoctors(@PageableDefault(size = 10) Pageable pagination) {
//        return doctorRepository.findAll(pagination).map(DataToListDoctor::new);
        return ResponseEntity.ok(doctorRepository.findByActiveTrue(pagination).map(DataToListDoctor::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseDoctor> updateDoctor(@RequestBody @Valid DataToUpdateDoctor dataToUpdateDoctor) {
        Doctor doctor = doctorRepository.getReferenceById(dataToUpdateDoctor.id());
        doctor.updateDataDoctor(dataToUpdateDoctor);
        return ResponseEntity.ok(new DataResponseDoctor(doctor.getId(), doctor.getNombre(), doctor.getEmail(),
                doctor.getTelefono(), doctor.getEspecialidad().toString(),
                new DataAddress(doctor.getDireccion().getCalle(), doctor.getDireccion().getDistrito(),
                        doctor.getDireccion().getCiudad(), doctor.getDireccion().getNumero(),
                        doctor.getDireccion().getComplemento())));
    }

    // LOGICAL DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseDoctor> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.deactivateDoctor();
        return ResponseEntity.noContent().build();
    }
    /* DELETE IN DATABASE
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        Doctor doc = doctorRepository.getReferenceById(id);
        doctorRepository.delete(doctor);
    }
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataResponseDoctor> returnsAllDataOneDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        var doctorData = new DataResponseDoctor(doctor.getId(), doctor.getNombre(), doctor.getEmail(),
                doctor.getTelefono(), doctor.getEspecialidad().toString(),
                new DataAddress(doctor.getDireccion().getCalle(), doctor.getDireccion().getDistrito(),
                        doctor.getDireccion().getCiudad(), doctor.getDireccion().getNumero(),
                        doctor.getDireccion().getComplemento()));
        return ResponseEntity.ok(doctorData);
    }
}





























