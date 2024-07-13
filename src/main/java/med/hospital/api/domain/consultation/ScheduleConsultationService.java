package med.hospital.api.domain.consultation;

import med.hospital.api.domain.consultation.validations.IValidatorConsultation;
import med.hospital.api.domain.doctor.Doctor;
import med.hospital.api.domain.doctor.IDoctorRepository;
import med.hospital.api.domain.patient.IPatientRepository;
import med.hospital.api.infra.Errors.ValidationIntegrity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleConsultationService {

    @Autowired //En caso no se agrege la anotación, el valor sería null, con @Autowired Spring instancia la clase, permitiendo usar sus métodos
    private IConsultationRepository consultationRepository;

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    List<IValidatorConsultation> validators; // Con la inyección de este atributo estamos pasando
    // los parametros de validación a la clase de servicio (*)
    // Así si queremos cambiar algo modificamos algún elemento de la interface
    // y no tocamos la clase

    public DataDetailsConsultation scheduleService(DataScheduleConsultation data) {

        //findById retorna un Optional, por lo cual se usa el método isPresent() para saber si se encontró
        if(!patientRepository.findById(data.idPatient()).isPresent()) {
            throw new ValidationIntegrity("This patient id was not found");
        }
        //existById retorna un booleano (true si se encuentra, false si no)
        if(data.idDoctor()!=null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationIntegrity("This doctor id was not found");
        }

        validators.forEach(V -> V.validate(data)); // (*) forEach para recorrer
        // todos los elemntos de la lista, conformada por cada uno de los validadores
        // Envía los datos (data) a cada uno de los validadores y verificar si cumple
        // con las condiciones deseadas para la clínica
        // CUMPLIMOS CON Principios SOLID y pilar de Polimorfismo

        var patient = patientRepository.findById(data.idPatient()).get();

        var doctor = selectDoctor(data);

        if(doctor==null) {
            throw new ValidationIntegrity("No doctors available for this time and specialty");
        }

        var consultation = new Consultation(null, doctor, patient, data.date());

        consultationRepository.save(consultation);

        return new DataDetailsConsultation(consultation);
    }

    /*
    private void cancelConsultation(DataScheduleConsultation data) {
        if(consultationRepository.existsById(data.id())) {
            throw new ValidationIntegrity("Consultation Id does not exist!");
        }
        validatorsCancelation.forEach(v -> v.validate(data));

        var consultation = consultationRepository.getReferenceById(data.id());
        consultation.cancel(data.reason());
    }
     */

    private Doctor selectDoctor(DataScheduleConsultation data) {
        //Algoritmo para seleccionar un médico aleatorio y que se encuentre disponible
        //en la fecha y que atienda las necesidades del paciente

        if(data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());//Retorna un doctor
            //A diferencia del Optional y el booleano, este me retorna un objeto
        }
        if (data.especialidad()==null) {
            throw new ValidationIntegrity("Must select a doctor specialty");
        }

        return doctorRepository.selectAvailableSpecialtyDoctorOnGivenDate(data.especialidad(), data.date());
    }
}





























