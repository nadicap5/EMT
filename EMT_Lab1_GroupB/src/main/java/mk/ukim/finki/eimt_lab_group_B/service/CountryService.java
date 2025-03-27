package mk.ukim.finki.eimt_lab_group_B.service;

import mk.ukim.finki.eimt_lab_group_B.model.Country;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto country);
    Optional<Country> update(Long id, CountryDto country);
    void deleteById(Long id);
}
