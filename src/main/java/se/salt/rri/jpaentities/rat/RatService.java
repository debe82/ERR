package se.salt.rri.jpaentities.rat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatus;
import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatusService;
import se.salt.rri.jpaentities.country.Country;
import se.salt.rri.jpaentities.ratStatus.RatStatus;
import se.salt.rri.jpaentities.ratStatus.RatStatusRepository;
import se.salt.rri.jpaentities.ratStatus.RatStatusService;
import se.salt.rri.models.*;
import se.salt.rri.jpaentities.city.City;
import se.salt.rri.jpaentities.city.CityService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatService {

  @Autowired
  RatRepository repo;
  @Autowired
  CityService cityService;

  @Autowired
  RatStatusService ratStatusService;

  @Autowired
  ClinicalStatusService clinicalService;

  public List<RescuedRat> getAllRats(){
    return repo.getAllRats();
  }

  //RatDto has all the info needed to be shown
  public RatDto convertRatToDto(RescuedRat rat) {
    String city = ""; //rat.getCity()
   return new RatDto(
           rat.getRescuedRatId(),
           rat.getName(),
           rat.getAge(),
           rat.getSex(),
           rat.getBreed(),
           rat.isSpayed(),
           clinicalService.convertRatStatusListToDto(rat.getRatstatuses()),
           rat.getCity().getName(),
           rat.getCity().getCountry().getName(),
           rat.getMedicalNumber(),
           rat.getDateOfRescue()
   );
  }

  public List<RatDto> convertRatListToDto(List<RescuedRat> rats){
    return rats.stream().map(rat -> convertRatToDto(rat)).collect(Collectors.toList());
  }

  public RescuedRat getRatByName(String name){
    return repo.findRescuedRatByName(name);
  }

  public Long getRatIdByName(String name){
    RescuedRat rat = getRatByName(name);
    return rat.getRescuedRatId();
  }

  public RescuedRat addRescuedRat(AddRatDto ratDto){
    City city = cityService.addNewCity(new AddCityDto(ratDto.cityName(), ratDto.countryName()));
    List<ClinicalStautsDto> statusList = new ArrayList<>();
    if (ratDto.statuses().size() != 0) statusList.addAll(ratDto.statuses());

    RescuedRat newRat = new RescuedRat(ratDto.name(), ratDto.breed(), ratDto.age(), ratDto.sex(), ratDto.spayed(), city);
    newRat.setMedicalNumber(UUID.randomUUID());
    repo.addNewRat(newRat);

    System.out.printf("newRat: " + newRat);

    List<ClinicalStatus> clinicalStatusList = clinicalService.convertStatusListFromDto(statusList);
    List<RatStatus> ratStatusList = new ArrayList<>();

    for (ClinicalStatus cs: clinicalStatusList) {
      ratStatusList.add(addRatStatus(newRat.getRescuedRatId(), cs));
    }
    newRat.setRatstatuses(ratStatusList);
    //return repo.addNewRat(newRat);
    return newRat;
  }

  public RescuedRat getRescuedRatById(Long id){
    return repo.findRescuedRatById(id);
  }

  public RescuedRat getRescuedRatByMedNumber(UUID medNumber){
    return repo.findRescuedRatByMedicalNumber(medNumber);
  }

  public void deleteRescuedRat(Long id) {
    RescuedRat rat = getRescuedRatById(id);
    repo.deleteRescuedRat(rat);

  }

  public RatStatus addRatStatus(long id, ClinicalStatus clinicalStatus) {
    RatStatus newRatStatus = new RatStatus();
    RescuedRat rat = getRescuedRatById(id);
    newRatStatus.setClinicalStatus(clinicalStatus);
    newRatStatus.setStartDate(new Date());
    newRatStatus.setCured(false);
    newRatStatus.setRat(rat);

    ratStatusService.addNewRatStatus(newRatStatus);

    return newRatStatus;
  }

  public RatDto updateRat(Long id, UpdRatDto rat) {
    RescuedRat ratToUpdate = getRescuedRatById(id);
    RatDto updRatDto = convertRatToDto(ratToUpdate);


    System.out.println("ratToUpdate: " + ratToUpdate);
    System.out.println("new values rat: " + rat);

    if(rat.name() != null && rat.name() != "") {
      ratToUpdate.setName(rat.name());
    }

    if(rat.age() > 0 && rat.age() < 36) {
      ratToUpdate.setAge(rat.age());
    }

    if(rat.breed() != null && rat.breed() != "") {
      ratToUpdate.setBreed(rat.breed());
    }

    if(rat.sex() != null && rat.sex() != "") {
      ratToUpdate.setSex(rat.sex());
    }

    if(rat.statuses() != null) {
      System.out.println("there are some news about the status");
      List<RatStatus> ratStatuses = ratToUpdate.getRatstatuses(); //get the list of statuses

      for (RatStatusDto ratStatusDto : rat.statuses()) {
        long ratStatusId = ratStatusDto.id();
        System.out.println("ratStatusId: " + ratStatusId);
        if (ratStatusId < 1) return null;

        if (ratStatusDto.title() != null && ratStatusDto.description() != null && ratStatusDto.medInstructions() != null) {
          System.out.println("status info available");
          //check if a new issue must be added

          boolean ratStatusFound = ratStatuses.stream()
                  .anyMatch(rs -> rs.getRatStatusId() == ratStatusId);

          if (ratStatusFound == false) {
//          if (ratToUpdate.getRatstatuses().get(Math.toIntExact(ratStatusId)) == null) { //maybe the get is not mapped
            System.out.println("the current status is not present in the rat history, creating a new status");
            ClinicalStatus cs = clinicalService.getStatusByTitle(ratStatusDto.title());
            System.out.println("new clinical status: " + cs);
            RatStatus ratStatus = addRatStatus(ratToUpdate.getRescuedRatId(), cs);
            System.out.println("new rat status added: " + ratStatus);
            ratStatuses.add(ratStatus);
          } else { //or else update
            System.out.println("status found, updating");
            int indexOfStatusToUpdate  = ratStatuses.indexOf(ratStatusService.getRatStatusById(ratStatusId));
            RatStatus ratStatusUpdated = ratStatusService.updRatStatus(ratStatusId, ratStatusDto);

            ratStatuses.set(indexOfStatusToUpdate ,ratStatusUpdated);

          }
        }
      }


      ratToUpdate.setRatstatuses(ratStatuses);
     // ratToUpdate.setRatStatuses(newStatusList);
    }

    ratToUpdate.setSpayed(rat.spayed());

    if((rat.cityName() != null && rat.cityName() != "")) {
      ratToUpdate.setCity(cityService.addNewCity(
              new AddCityDto(rat.cityName(),
                      ratToUpdate.getCity().getCountry().getName())));
    }

    if((rat.countryName() != null && rat.countryName() != "")) {
      ratToUpdate.setCity(cityService.addNewCity(new AddCityDto(ratToUpdate.getCity().getName(), rat.countryName())));
    }

    System.out.println("ratToUpdate after update: " + ratToUpdate);

    RatDto ratUpdatedDto = convertRatToDto(repo.update(ratToUpdate));
    return ratUpdatedDto;
  }

}
