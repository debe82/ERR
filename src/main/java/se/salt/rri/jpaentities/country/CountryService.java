package se.salt.rri.jpaentities.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepo;

    public Country addNewCountry(String countryName) {
        Country country = countryRepo.addNewCountry(countryName);
        if (country == null) return null;
        return country;
    }

    public List<Country> getAllCountries() {
        //List<Country> listOfCountries = countryRepo.getAllCountries();
        return  countryRepo.getAllCountries();
    }

    public Country getCountryByID(Long id) {
        return countryRepo.getCountryById(id);
    }

    public Country getCountryByName(String countryName) { return countryRepo.getCountryByName(countryName); }

    public void deleteCountry(Long id) {
        countryRepo.deleteCountry(id);
    }
}
