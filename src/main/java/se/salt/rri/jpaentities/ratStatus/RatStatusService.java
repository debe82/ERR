package se.salt.rri.jpaentities.ratStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatus;
import se.salt.rri.jpaentities.rat.RatService;
import se.salt.rri.jpaentities.rat.RescuedRat;
import se.salt.rri.models.RatStatusDto;

import java.util.Date;

@Service
public class RatStatusService {

    @Autowired
    RatStatusRepository ratStatusRepo;

    public RatStatus getRatStatusById(long id) {
        return ratStatusRepo.getStatusById(id);
    }

    public RatStatus updRatStatus(long id, RatStatusDto dto) {
        System.out.println("info to update: " + dto);
        RatStatus ratStatusToUpdate = getRatStatusById(id);
        System.out.println("rat status to update: " + ratStatusToUpdate);
        //not working, to check
        //da aggiornare: medical instructions can change during the course
        if (dto.stop() != null) { ratStatusToUpdate.setEndDate(dto.stop()); }
        ratStatusToUpdate.setCured(dto.cured());
        System.out.println("ratStatus after update: " + ratStatusToUpdate);
        return ratStatusRepo.updRatStatus(ratStatusToUpdate);
    }

    public RatStatus addNewRatStatus(RatStatus newRatStatus) {
        return ratStatusRepo.addNewRatStatus(newRatStatus);
    }
}
