package med.hospital.api.infra.Errors;

public class ValidationIntegrity extends RuntimeException {
    //Throwable responde ante errores y excepciones, RuntimeException solo responde antes excepciones
    public ValidationIntegrity(String s) {
        super(s);
    }
}
