package med.hospital.api.domain.consultation.validations;

import med.hospital.api.domain.consultation.DataScheduleConsultation;

public interface IValidatorConsultation {
// Principio de Inversión de Dependencias: Las clases de alto nivel
// tienen que depender de abstracciones o interfaces y no de clases de bajo nivel
// Clases de alto nivel: son las que se encuentran relacionadas directamente
// con las reglas de negocio, por ejemplo: buscar la consulta, guardar una consulta,
// buscar alguna de las consultas por id, asignar nuevas consultas por id
// Clases de bajo nivel: por ejemplo las que se encargan de realizar conexiones
    public void validate(DataScheduleConsultation data);
}
