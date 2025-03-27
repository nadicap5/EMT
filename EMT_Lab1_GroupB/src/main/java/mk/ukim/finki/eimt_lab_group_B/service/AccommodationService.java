package mk.ukim.finki.eimt_lab_group_B.service;

import mk.ukim.finki.eimt_lab_group_B.model.Accommodation;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(AccommodationDto accommodation);
    Optional<Accommodation> update(Long id, AccommodationDto accommodation);
    void deleteById(Long id);

    Optional<Accommodation> rentAccommodation(Long id);
}
