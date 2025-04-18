package mk.ukim.finki.lab1b.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1b.dto.CreateAccommodationDto;
import mk.ukim.finki.lab1b.model.domain.User;
import mk.ukim.finki.lab1b.model.enumerations.Category;
import mk.ukim.finki.lab1b.model.domain.Country;
import mk.ukim.finki.lab1b.model.domain.Host;
import mk.ukim.finki.lab1b.model.enumerations.Role;
import mk.ukim.finki.lab1b.repository.CountryRepository;
import mk.ukim.finki.lab1b.repository.HostRepository;
import mk.ukim.finki.lab1b.repository.UserRepository;
import mk.ukim.finki.lab1b.web.AccommodationController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationController accommodationController;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public DataInitializer(AccommodationController accommodationController, HostRepository hostRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationController = accommodationController;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    public void init() {
        Country c1=countryRepository.save(new Country("Italy","Europe"));
        Country c2= countryRepository.save(new Country("Spain","Europe"));
        Country c3=countryRepository.save(new Country("Macedonia","Europe"));



        Host h1= hostRepository.save(new Host("Nadica","Petkovska",c1));
        Host h2=hostRepository.save(new Host("Jovana","Petkovska",c2));
        Host h3=hostRepository.save(new Host("Ivan","Zdravkovski",c2));


        accommodationController.save(new CreateAccommodationDto("Family", Category.APARTMENT,h1,3));
        accommodationController.save(new CreateAccommodationDto("Solo", Category.FLAT,h2,1));
        accommodationController.save(new CreateAccommodationDto("Couple", Category.HOTEL,h2,1));

        userRepository.save(new User(
                "ho",
                passwordEncoder.encode("ho"),
                "User",
                "User",
                Role.ROLE_USER
        ));

        userRepository.save(new User(
                "us",
                passwordEncoder.encode("us"),
                "host name",
                "host name",
                Role.ROLE_HOST
        ));
    }

}
