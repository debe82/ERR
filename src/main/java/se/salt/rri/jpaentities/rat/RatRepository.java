package se.salt.rri.jpaentities.rat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RatRepository implements IRatRepository{

  @Autowired
  JpaRatRepository ratRepo;

  @Override
  public List<RescuedRat> getAllRats() {
    Iterable<RescuedRat> all = ratRepo.findAll();
    List<RescuedRat> rats = Streamable.of(all).toList();
    return rats;
  }

  @Override
  public RescuedRat findRescuedRatByName(String name) {
    return ratRepo.findRescuedRatByName(name);
  }

  @Override
  public RescuedRat findRescuedRatById(Long id) {
    return ratRepo.findById(id).orElse(null);
  }

  @Override
  public RescuedRat findRescuedRatByMedicalNumber(UUID medNumber) {
    return ratRepo.findRescuedRatByMedicalNumber(medNumber);
  }


  @Override
  public RescuedRat addNewRat(RescuedRat rat) {
    return ratRepo.save(rat);
  }

  @Override
  public RescuedRat update(RescuedRat rat) {
    return ratRepo.save(rat);
  }

  @Override
  public void deleteRescuedRat(RescuedRat rat) {
    ratRepo.delete(rat);
  }

}
