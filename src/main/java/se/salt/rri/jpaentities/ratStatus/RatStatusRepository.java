package se.salt.rri.jpaentities.ratStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RatStatusRepository {
    @Autowired
    IJpaRatStatusRepository jpaRepo;

    public RatStatus addNewRatStatus(RatStatus ratStatus) {
        return jpaRepo.save(ratStatus);
    }
}
