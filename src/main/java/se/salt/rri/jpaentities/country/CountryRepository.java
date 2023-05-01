package se.salt.rri.jpaentities.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import se.salt.rri.jpaentities.city.City;

import java.util.List;

@Repository
public class CountryRepository implements ICountryRepository{

    @Autowired
    JpaCountryRepository jpaRepo;

    public Country addNewCountry (String countryName) {
        Country countryToAdd = jpaRepo.findByName(countryName);
        if (countryToAdd != null) return null;
        return jpaRepo.save(new Country(countryName));
    }

    public Country getCountryById (Long id) {
        Country country = jpaRepo.findById(id).orElse(null);
        if (country == null) return null;
        return country;
    }

    public Country getCountryByName(String countryName) {
        Country country = jpaRepo.findByName(countryName);
        return country;
    }

    public List<Country> getAllCountries() {
        Iterable<Country> all = jpaRepo.findAll();
        return Streamable.of(all).toList();
    }

    public void deleteCountry(Long id) {
        jpaRepo.deleteById(id);
    }
}
