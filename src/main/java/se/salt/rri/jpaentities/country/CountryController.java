package se.salt.rri.jpaentities.country;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired CountryService service;

    @GetMapping
    ResponseEntity<List<Country>> getAllCountries(){
        List<Country> listOfCountries = service.getAllCountries();
        return ResponseEntity.ok().body(listOfCountries);
    }

    @GetMapping("{id}")
    ResponseEntity<Country> getCountry(@PathVariable Long id){
        if (id < 1) return ResponseEntity.badRequest().build();
        if (service.getCountryByID(id) == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(service.getCountryByID(id));
    }

    @PostMapping
    ResponseEntity<Country> addCountry(@RequestBody Country country, HttpServletRequest req){
        Country countryToAdd = service.addNewCountry(country.getName());
        URI location = URI.create(req.getRequestURL() + "/" + countryToAdd.getCountryId());
        if (countryToAdd == null) return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.created(location).body(countryToAdd);
    }

    @DeleteMapping("{id}")
    ResponseEntity deleteCountry(@PathVariable Long id){
        if(id < 1) return ResponseEntity.badRequest().build();
        Country country = service.getCountryByID(id);
        if(country == null) return ResponseEntity.notFound().build();
        service.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
