package se.salt.rri.jpaentities.country;

import org.springframework.data.repository.CrudRepository;
import se.salt.rri.jpaentities.city.City;

import java.util.List;

public interface JpaCountryRepository extends CrudRepository<Country, Long> {

    Country findByName(String countryName);

}
