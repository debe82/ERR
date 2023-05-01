package se.salt.rri.jpaentities.rat;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatus;
import se.salt.rri.jpaentities.ratStatus.RatStatus;
import se.salt.rri.models.AddRatDto;
import se.salt.rri.models.RatDto;
import se.salt.rri.models.UpdRatDto;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rats")
public class RatController {
  @Autowired
  RatService service;

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping
  ResponseEntity<List<RatDto>> getAllRats(){
    List<RescuedRat> listOfRats = service.getAllRats();
    List<RatDto> showAllRatsInfos = service.convertRatListToDto(listOfRats);
    return ResponseEntity.ok().body(showAllRatsInfos);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("{id}")
  ResponseEntity<RatDto> getRatById(@PathVariable Long id){
    if (id < 1) return ResponseEntity.badRequest().build();
    if (service.getRescuedRatById(id) == null) return ResponseEntity.notFound().build();
    RescuedRat rat = service.getRescuedRatById(id);
    RatDto dto = service.convertRatToDto(rat);

    return ResponseEntity.ok(dto);
  }

  @GetMapping("/number/{medNum}")
  ResponseEntity<RescuedRat> getRatByMedNum(@PathVariable UUID medNum){
    if (medNum == null) return ResponseEntity.badRequest().build();
    if (service.getRescuedRatByMedNumber(medNum) == null) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(service.getRescuedRatByMedNumber(medNum));
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping
  ResponseEntity<RescuedRat> addRescuedRat(@RequestBody AddRatDto addRatDto, HttpServletRequest req){
    RescuedRat rat = service.addRescuedRat(addRatDto);
    System.out.println("written rat: " + rat);
    if (rat == null) return ResponseEntity.unprocessableEntity().build();
    URI location = URI.create(req.getRequestURL() + "/" + rat.getRescuedRatId());
    return ResponseEntity.created(location).body(rat);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PutMapping(path="{id}")
  ResponseEntity<RescuedRat> updateRescuedRat(@PathVariable Long id, @RequestBody UpdRatDto updRatDto){
    if (id < 1) return ResponseEntity.badRequest().build();
    RescuedRat updatedRat = service.updateRat(id, updRatDto);
    if (updatedRat == null) return ResponseEntity.notFound().build();
    return ResponseEntity.accepted().body(updatedRat);
  }

  @PostMapping("{id}/status")
  public ResponseEntity<RatStatus> addRatStatus(@PathVariable long id, @RequestBody ClinicalStatus clinicalStatus
  ) {
    if (id < 0) return ResponseEntity.badRequest().build();
    RatStatus ratStatus = service.addRatStatus(id, clinicalStatus);
    if (ratStatus == null) return ResponseEntity.notFound().build();
    URI location = URI.create(("/api/rats/" + ratStatus.getRatStatusId() + "/status"));
    return ResponseEntity.created(location).body(ratStatus);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @DeleteMapping("{id}")
  ResponseEntity deleteRescuedRat(@PathVariable Long id){
    if(id < 1) return ResponseEntity.badRequest().build();
    RescuedRat rat = service.getRescuedRatById(id);
    if(rat == null) return ResponseEntity.notFound().build();
    service.deleteRescuedRat(id);
    return ResponseEntity.noContent().build();
  }



}
