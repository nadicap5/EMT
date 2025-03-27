package mk.ukim.finki.eimt_lab_group_B.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.eimt_lab_group_B.model.Accommodation;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.AccommodationDto;
import mk.ukim.finki.eimt_lab_group_B.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation Controller", description = "API for managing accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    @Operation(summary = "Get all accommodations", description = "Retrieve all accommodations")
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get accommodation by id", description = "Retrieve accommodation by id")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(a-> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add new accommodation", description = "Create and store a new accommodation")
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDto accommodation) {
        return accommodationService.save(accommodation)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit accommodation", description = "Edit an existing accommodation by id")
    public ResponseEntity<Accommodation> update(@PathVariable Long id, @RequestBody AccommodationDto accommodation) {
        return accommodationService.update(id, accommodation)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete accommodation", description = "Delete accommodation by id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if(accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/rent/{id}")
    @Operation(summary = "Rent an accommodation", description = "Decrease a number of rooms when accommodation is rented")
    public ResponseEntity<Accommodation> rentAccommodation(@PathVariable Long id) {
        return accommodationService.rentAccommodation(id)
                .map(a->ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

