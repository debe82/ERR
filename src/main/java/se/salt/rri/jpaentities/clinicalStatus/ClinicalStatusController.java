package se.salt.rri.jpaentities.clinicalStatus;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.salt.rri.jpaentities.city.City;
import se.salt.rri.jpaentities.rat.RatService;
import se.salt.rri.jpaentities.rat.RescuedRat;
import se.salt.rri.models.AddRatDto;
import se.salt.rri.models.ClinicalStautsDto;
import se.salt.rri.models.RatDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clinicalstatus")
public class ClinicalStatusController {

    @Autowired
    ClinicalStatusService service;

    @Autowired
    RatService ratService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    ResponseEntity<List<ClinicalStatus>> getAllStatuses(){
        List<ClinicalStatus> listOfStatuses = service.getAllStatuses();
        return ResponseEntity.ok().body(listOfStatuses);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{id}")
    ResponseEntity<ClinicalStatus> getClinicalStatus(@PathVariable Long id){
        if (id < 1) return ResponseEntity.badRequest().build();
        if (service.getClinicalStatus(id) == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(service.getClinicalStatus(id));
    }

    @PostMapping
    ResponseEntity<List<ClinicalStatus>> addStatus(@RequestBody List<ClinicalStautsDto> statusDto, HttpServletRequest req){
        List<ClinicalStatus> newClinicalStatus = service.AddNewStatus(statusDto);
        System.out.println("clinicalStatusController.addStatus.newClinicalStatus: " + newClinicalStatus);

        if (newClinicalStatus == null) return ResponseEntity.unprocessableEntity().build();
        //URI location = URI.create(req.getRequestURL() + "/" + newClinicalStatus.getClinicalStatusId());

        return ResponseEntity.ok(newClinicalStatus);//ResponseEntity.created(location).body(newClinicalStatus);
    }
    @DeleteMapping("{id}")
    ResponseEntity deleteStatus(@PathVariable Long id){
        if(id < 1) return ResponseEntity.badRequest().build();
        ClinicalStatus status = service.getClinicalStatus(id);
        if(status == null) return ResponseEntity.notFound().build();
        service.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }

}
