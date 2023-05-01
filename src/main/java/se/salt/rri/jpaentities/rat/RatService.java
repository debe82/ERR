package se.salt.rri.jpaentities.rat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatus;
import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatusService;
import se.salt.rri.jpaentities.country.Country;
import se.salt.rri.jpaentities.ratStatus.RatStatus;
import se.salt.rri.jpaentities.ratStatus.RatStatusRepository;
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
  RatStatusRepository ratStatusRepo;
  @Autowired
  CityService cityService;

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

  public RescuedRat updateRat(Long id, UpdRatDto rat) {
    RescuedRat ratToUpdate = getRescuedRatById(id);
    RatDto updRatDto = convertRatToDto(ratToUpdate);


    System.out.println("ratToUpdate: " + ratToUpdate);
    System.out.println("new values rat: " + rat);
    System.out.println("updRatDto:" + updRatDto);

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
      List<ClinicalStautsDto> dtoStatusList = new ArrayList<>();
      for (RatStatusDto ratStatusDto : rat.statuses()) {
        //check if a new issue must be added
        if (ratStatusDto.title() != null && ratStatusDto.description() != null && ratStatusDto.medInstructions() != null) {
          dtoStatusList.add(
                  new ClinicalStautsDto(ratStatusDto.title(),
                          ratStatusDto.description(),
                          ratStatusDto.medInstructions()));
        } else {
          //check if the stop must be updated
          if (ratStatusDto.stop() != null) {


          }

        }
      }
      List<ClinicalStatus> newStatusList = clinicalService.AddNewStatus(null);
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


    return repo.update(ratToUpdate);
  }

  public RatStatus addRatStatus(long id, ClinicalStatus clinicalStatus) {
    RatStatus newRatStatus = new RatStatus();
    RescuedRat rat = getRescuedRatById(id);
    newRatStatus.setClinicalStatus(clinicalStatus);
    newRatStatus.setStartDate(new Date());
    newRatStatus.setCured(false);
    newRatStatus.setRat(rat);

    ratStatusRepo.addNewRatStatus(newRatStatus);

    return newRatStatus;
  }
}
