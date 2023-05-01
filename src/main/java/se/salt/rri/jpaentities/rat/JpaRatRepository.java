package se.salt.rri.jpaentities.rat;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaRatRepository extends CrudRepository<RescuedRat, Long> {

  RescuedRat findRescuedRatByName(String name);

  RescuedRat findRescuedRatByMedicalNumber(UUID medNumber);

}
