package se.salt.rri.jpaentities.rat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import se.salt.rri.jpaentities.city.City;
import se.salt.rri.jpaentities.clinicalStatus.ClinicalStatus;
import se.salt.rri.jpaentities.ratStatus.RatStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class RescuedRat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rescuedRatId;

  private String name;
  private String breed;
  private int age;
  private String sex;

  @OneToOne
  private City city;
  private boolean spayed;

  @OneToMany(mappedBy="rat")
  private List<RatStatus> ratstatuses;

  private UUID medicalNumber;

  private Date dateOfRescue;

  public RescuedRat(){
    dateOfRescue = new Date();
  };

  public RescuedRat(String name, String breed, int age, String sex, boolean spayed, City city) {
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.sex = sex;
    this.spayed = spayed;
    this.city = city;
    dateOfRescue = new Date();
  }

  public RescuedRat(String name, String breed, int age, String sex, City city, boolean spayed) {
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.sex = sex;
    this.city = city;
    this.spayed = spayed;
    dateOfRescue = new Date();
  }

  public Long getRescuedRatId() {
    return rescuedRatId;
  }

  public void setRescuedRatId(Long rescuedRatId) {
    this.rescuedRatId = rescuedRatId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public List<RatStatus> getRatstatuses() {
    return ratstatuses;
  }

  public void setRatstatuses(List<RatStatus> ratstatuses) {
    this.ratstatuses = ratstatuses;
  }

  public boolean isSpayed() {
    return spayed;
  }

  public void setSpayed(boolean spayed) {
    this.spayed = spayed;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public UUID getMedicalNumber() {
    return medicalNumber;
  }

  public void setMedicalNumber(UUID medicalNumber) {
    this.medicalNumber = medicalNumber;
  }

  public Date getDateOfRescue() {
    return dateOfRescue;
  }

  public void setDateOfRescue(Date dateOfRescue) {
    this.dateOfRescue = dateOfRescue;
  }

  @Override
  public String toString() {
    return "RescuedRat{" +
            "rescuedRatId=" + rescuedRatId +
            ", name='" + name + '\'' +
            ", breed='" + breed + '\'' +
            ", age=" + age +
            ", sex='" + sex + '\'' +
            ", city=" + city +
            ", spayed=" + spayed +
            ", medicalNumber=" + medicalNumber +
            ", dateOfRescue=" + dateOfRescue +
            '}';
  }
}
