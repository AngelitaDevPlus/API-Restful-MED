package med.hospital.api.controller;

import jakarta.validation.Valid;
import med.hospital.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private IPatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerPatient(@RequestBody @Valid DataToRegisterPatient data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        patientRepository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailsPatient(patient));
    }

    @GetMapping
    public ResponseEntity<Page<DataToListPatient>> listingPatients(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        var page = patientRepository.findAllByActiveTrue(paginacion).map(DataToListPatient::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatient(@RequestBody @Valid DataToUpdatePatient data) {
        var patient = patientRepository.getReferenceById(data.id());
        patient.updateDataPatient(data);

        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.deactivatePatient();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity returnsAllDataOnePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }
}
