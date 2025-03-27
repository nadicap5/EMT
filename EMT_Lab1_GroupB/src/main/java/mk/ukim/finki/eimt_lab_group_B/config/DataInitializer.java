package mk.ukim.finki.eimt_lab_group_B.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimt_lab_group_B.model.Accommodation;
import mk.ukim.finki.eimt_lab_group_B.model.Country;
import mk.ukim.finki.eimt_lab_group_B.model.Host;
import mk.ukim.finki.eimt_lab_group_B.model.enumerations.AccommodationCategory;
import mk.ukim.finki.eimt_lab_group_B.repository.AccommodationRepository;
import mk.ukim.finki.eimt_lab_group_B.repository.CountryRepository;
import mk.ukim.finki.eimt_lab_group_B.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, HostRepository hostRepository, CountryRepository countryRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        Country country1 = countryRepository.save(new Country("Country1", "Continent1"));
        Country country2 = countryRepository.save(new Country("Country2", "Continent2"));
        Country country3 = countryRepository.save(new Country("Country3", "Continent3"));


        Host host1 = hostRepository.save(new Host("HostName1" ,"HostSurname1", country1));
        Host host2 = hostRepository.save(new Host("HostName2" ,"HostSurname2", country2));
        Host host3 = hostRepository.save(new Host("HostName3" ,"HostSurname3", country3));


        accommodationRepository.save(new Accommodation("Accommodation1", AccommodationCategory.APARTMENT, host1, 10));
        accommodationRepository.save(new Accommodation("Accommodation2", AccommodationCategory.HOTEL, host2, 25));
        accommodationRepository.save(new Accommodation("Accommodation3", AccommodationCategory.MOTEL, host3, 40));

    }
}

