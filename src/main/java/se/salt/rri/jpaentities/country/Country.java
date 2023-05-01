package se.salt.rri.jpaentities.country;

import jakarta.persistence.*;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long countryId;

    String name;

/*
    @OneToMany
    List<City> cities;
*/

    public Long getCountryId() {
        return countryId;
    }

    public Country() {
    }

    public Country(String name) {

        this.name = name;
//        this.cities = new ArrayList<>();
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }*/

    @Override
    public String toString() {
        return "Country{" +
                "id=" + countryId +
                ", name='" + name + '\'' +
//                ", cities=" + cities +
                '}';
    }
}
