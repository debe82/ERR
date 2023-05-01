package se.salt.rri.jpaentities.rat;

import java.util.List;
import java.util.UUID;

public interface IRatRepository {
  List<RescuedRat> getAllRats();
  RescuedRat findRescuedRatByName(String name);

  RescuedRat findRescuedRatById(Long id);

  RescuedRat findRescuedRatByMedicalNumber(UUID medNumber);


  RescuedRat addNewRat(RescuedRat rat);
  RescuedRat update(RescuedRat rat);
  void deleteRescuedRat(RescuedRat rat);
}
