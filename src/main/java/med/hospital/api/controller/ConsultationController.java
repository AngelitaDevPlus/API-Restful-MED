package med.hospital.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.hospital.api.domain.consultation.DataDetailsConsultation;
import med.hospital.api.domain.consultation.DataScheduleConsultation;
import med.hospital.api.domain.consultation.ScheduleConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//Estas anotaciones indican que estas clases son componentes manejados por Spring
//Quiere decir que no tenemos que instanciar la clase (new ConsultationController())
//para hacer uso de esta clase, Spring la instancia y la deja disponible para nuestro uso
//@RestController = @Controller + @ResponseBody
//@Controller = @Component

@Controller
@ResponseBody
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ScheduleConsultationService service;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid DataScheduleConsultation data) {
        service.scheduleService(data);
        return ResponseEntity.ok(new DataDetailsConsultation(null, null, null, null));
    }


}
