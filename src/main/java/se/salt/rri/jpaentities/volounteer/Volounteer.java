package se.salt.rri.jpaentities.volounteer;

import jakarta.persistence.*;
import se.salt.rri.jpaentities.city.City;
import se.salt.rri.jpaentities.rat.RescuedRat;

import java.util.Date;
import java.util.List;

@Entity
public class Volounteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long volounteerId;

    private String fullName;
    private String email;
    private List<String> otherContacts;
    private Date startDate;

    @OneToMany
    private List<RescuedRat> rescuedRats;

//    @OneToMany
//    private List<VolounteerAdopted> volounteerAdopted;

    @OneToOne
    private City city;
}
