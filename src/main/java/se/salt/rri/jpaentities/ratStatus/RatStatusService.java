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
        RatStatus rs = getRatStatusById(id);
        //not working, to check
        //da aggiornare: medical instructions can change during the course
        if (rs.getEndDate() != null) { rs.setEndDate(dto.stop()); }
        rs.setCured(dto.cured());

        return ratStatusRepo.updRatStatus(rs);
    }

    public RatStatus addNewRatStatus(RatStatus newRatStatus) {
        return ratStatusRepo.addNewRatStatus(newRatStatus);
    }
}
