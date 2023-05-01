package se.salt.rri.jpaentities.city;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.salt.rri.models.AddCityDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    CityService service;

    @GetMapping
    ResponseEntity<List<City>> getAllCity(){
        List<City> listOfCities = service.getAllCities();
        return ResponseEntity.ok().body(listOfCities);
    }

    @GetMapping("{id}")
    ResponseEntity<City> getCity(@PathVariable Long id){
        if (id < 1) return ResponseEntity.badRequest().build();
        if (service.getCityById(id) == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(service.getCityById(id));
    }

    @PostMapping
    ResponseEntity<City> addCity(@RequestBody AddCityDto cityDto, HttpServletRequest req){
        City newCity = service.addNewCity(cityDto);
        if (newCity == null) return ResponseEntity.unprocessableEntity().build();
        //URI location = URI.create(req.getRequestURL() + "/" + newCity.getId());
        return ResponseEntity.ok(newCity);
    }

    @DeleteMapping("{id}")
    ResponseEntity deletCity(@PathVariable Long id){
        if(id < 1) return ResponseEntity.badRequest().build();
        City city = service.getCityById(id);
        if(city == null) return ResponseEntity.notFound().build();
        service.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

}
