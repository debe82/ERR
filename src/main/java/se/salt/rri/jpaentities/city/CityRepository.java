package se.salt.rri.jpaentities.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository implements ICityRepository{
  @Autowired
  JpaCityRepository jpaRepo;

  public City addNewCity(String city) {
    City cityToAdd = jpaRepo.findByName(city);
    if (cityToAdd != null) {
      System.out.println("cityRepo.addNewCity.ifExist.cityToAdd: " + cityToAdd);
      return null;
    }
    City newCity = jpaRepo.save(new City(city));
    System.out.println("cityRepo.addNewCity.ifNotExist.newCity: " + newCity);
    return newCity;
  }

  public City updateCity(City city) {
    City cityToUpd = jpaRepo.findByName(city.getName());
    if (cityToUpd != null) {
      jpaRepo.save(cityToUpd);
      System.out.println("cityRepo.updateCity.cityToUpd: " + cityToUpd);
      return null;
    }

    return null;
  }

  public City getCityByName(String cityName) {
    City city = jpaRepo.findByName(cityName);
    //City city = jpaRepo.getCityWitCountry(cityName);
    return city;
  }

  public List<City> getAllCities() {
    Iterable<City> all = jpaRepo.findAll();
    return Streamable.of(all).toList();
  }

  public City getCityById(Long id) {
    return jpaRepo.findById(id).orElse(null);
  }

  public void deleteCity(Long id) {
    jpaRepo.deleteById(id);
  }

}
