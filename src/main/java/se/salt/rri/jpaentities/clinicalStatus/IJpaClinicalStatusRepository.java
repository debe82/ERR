package se.salt.rri.jpaentities.clinicalStatus;

import org.springframework.data.repository.CrudRepository;
import se.salt.rri.jpaentities.rat.RescuedRat;

public interface IJpaClinicalStatusRepository extends CrudRepository<ClinicalStatus, Long> {

    ClinicalStatus findByTitle (String title);
}
