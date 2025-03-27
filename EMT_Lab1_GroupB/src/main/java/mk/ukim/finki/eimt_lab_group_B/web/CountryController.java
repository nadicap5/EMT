package mk.ukim.finki.eimt_lab_group_B.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.eimt_lab_group_B.model.Country;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.CountryDto;
import mk.ukim.finki.eimt_lab_group_B.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country Controller", description = "API for managing countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Get all countries", description = "Retrieve a list of all countries")
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get country by id", description = "Retrieve country by id")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return this.countryService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new country", description = "Create and store a new country")
    public ResponseEntity<Country> save(@RequestBody CountryDto country) {
        return this.countryService.save(country)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit country", description = "Edit existing country by id")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody CountryDto country) {
        return this.countryService.update(id, country)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete country", description = "Delete country by id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
