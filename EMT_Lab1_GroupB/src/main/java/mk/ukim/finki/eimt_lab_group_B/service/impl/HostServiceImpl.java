package mk.ukim.finki.eimt_lab_group_B.service.impl;

import mk.ukim.finki.eimt_lab_group_B.model.Country;
import mk.ukim.finki.eimt_lab_group_B.model.Host;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.HostDto;
import mk.ukim.finki.eimt_lab_group_B.repository.CountryRepository;
import mk.ukim.finki.eimt_lab_group_B.repository.HostRepository;
import mk.ukim.finki.eimt_lab_group_B.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(HostDto host) {
        if (host.getCountryId() != null) {
            Optional<Country> country = countryRepository.findById(host.getCountryId());
            if (country.isPresent()) {
                Host newHost = new Host(host.getName(), host.getSurname(), country.get());
                return Optional.of(hostRepository.save(newHost));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, HostDto host) {
        return hostRepository.findById(id).map(existingHost ->{
            if(host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if(host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if(host.getCountryId() != null) {
                Optional<Country> country = countryRepository.findById(host.getCountryId());
                country.ifPresent(existingHost::setCountry);
            }
            return hostRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}

