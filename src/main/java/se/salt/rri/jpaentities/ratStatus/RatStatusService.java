package se.salt.rri.jpaentities.ratStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salt.rri.models.RatStatusDto;

@Service
public class RatStatusService {

    @Autowired
    RatStatusRepository ratStatusRepo;

    public RatStatus getRatStatusById(long id) {
        return ratStatusRepo.getStatusById(id);
    }

    public RatStatus updRatStatus(long id, RatStatusDto dto) {
        RatStatus ratStatusToUpdate = getRatStatusById(id);
        if (dto.stop() != null) { ratStatusToUpdate.setEndDate(dto.stop()); }
        ratStatusToUpdate.setCured(dto.cured());
        return ratStatusRepo.updRatStatus(ratStatusToUpdate);
    }

    public RatStatus addNewRatStatus(RatStatus newRatStatus) {
        return ratStatusRepo.addNewRatStatus(newRatStatus);
    }
}
