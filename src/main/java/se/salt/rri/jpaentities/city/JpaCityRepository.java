package se.salt.rri.jpaentities.city;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface JpaCityRepository extends CrudRepository<City, Long> {
    City findByName(String city);

    @Query("select c from City c join fetch c.country where c.name = ?1")
    City getCityWitCountry(String cityName);
}
