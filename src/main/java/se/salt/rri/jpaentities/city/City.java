package se.salt.rri.jpaentities.city;

import jakarta.persistence.*;
import se.salt.rri.jpaentities.country.Country;
import se.salt.rri.jpaentities.rat.RescuedRat;

@Entity
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String name;

  @ManyToOne
  //@JoinColumn(name = "countryId")
  Country country;

//  @OneToOne(mappedBy = "city")
//  private RescuedRat rat;
  public City (){}

  public City(String name, Country country){
    this.name = name;
    this.country = country;
  }

  public City(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "City{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", country=" + country +
            '}';
  }
}
