package se.salt.rri.jpaentities.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salt.rri.jpaentities.country.Country;
import se.salt.rri.jpaentities.country.CountryService;
import se.salt.rri.models.AddCityDto;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepo;
    @Autowired
    CountryService countryService;

    public City addNewCity (AddCityDto cityDto) {
        City city = getCityByName(cityDto.cityName());
        Country country = countryService.getCountryByName(cityDto.countryName());



        City newCity = city;
        if (city == null && country == null) {
            Country newCountry = countryService.addNewCountry(cityDto.countryName());
            newCity = cityRepo.addNewCity(cityDto.cityName());
            newCity.setCountry(newCountry);
            cityRepo.updateCity(newCity);
        }

        if (city == null && country != null) {
            newCity = cityRepo.addNewCity(cityDto.cityName());
            newCity.setCountry(country);
            cityRepo.updateCity(newCity);
        }

        if (city != null && country == null) {
            Country newCountry = countryService.addNewCountry(cityDto.countryName());
            newCity = cityRepo.addNewCity("cityToAdd");
            newCity.setName(city.getName());
            newCity.setCountry(newCountry);
            cityRepo.updateCity(newCity);
        }

        if ((country != null && city != null) && !city.getCountry().getName().equals(country.getName())){
            newCity = cityRepo.addNewCity("cityToAdd");
            newCity.setName(city.getName());
            newCity.setCountry(country);
            cityRepo.updateCity(newCity);
        }

        return newCity;
    }

    public City getCityByName (String cityName) {
        City city = cityRepo.getCityByName(cityName);
        if (city == null) return null;
        return city;
    }

    public Long getCityIdByName (String cityName) {
        City city = cityRepo.getCityByName(cityName);
        if (city == null) return -1L;
        return city.getId();
    }

    public List<City> getAllCities() {
        List<City> cities = cityRepo.getAllCities();
        return cities;
    }

    public City getCityById(Long id) {
        City city = cityRepo.getCityById(id);
        return city;
    }

    public void deleteCity(Long id) {
        cityRepo.deleteCity(id);
    }
}
