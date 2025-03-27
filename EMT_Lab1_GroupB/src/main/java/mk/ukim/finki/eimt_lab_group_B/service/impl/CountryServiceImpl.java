package mk.ukim.finki.eimt_lab_group_B.service.impl;

import mk.ukim.finki.eimt_lab_group_B.model.Country;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.CountryDto;
import mk.ukim.finki.eimt_lab_group_B.repository.CountryRepository;
import mk.ukim.finki.eimt_lab_group_B.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto country) {
        Country newCountry = new Country();
        newCountry.setName(country.getName());
        newCountry.setContinent(country.getContinent());
        return Optional.of(countryRepository.save(newCountry));
    }

    @Override
    public Optional<Country> update(Long id, CountryDto country) {
        return countryRepository.findById(id).map(existingCountry -> {
            if (country.getName() != null) {
                existingCountry.setName(country.getName());
            }
            if (country.getContinent() != null) {
                existingCountry.setContinent(country.getContinent());
            }
            return countryRepository.save(existingCountry);
        });
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
