package se.salt.rri.jpaentities.ratStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.salt.rri.jpaentities.rat.RescuedRat;
import se.salt.rri.models.RatDto;

@RestController
@RequestMapping("/api/statuses")
public class RatStatusController {

    @Autowired
    RatStatusService ratStatusService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{id}")
    ResponseEntity<RatStatus> getStatusById(@PathVariable Long id){

        if (id < 1) return ResponseEntity.badRequest().build();
        RatStatus ratStatus = ratStatusService.getRatStatusById(id);
        if (ratStatus == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ratStatus);
    }
}
