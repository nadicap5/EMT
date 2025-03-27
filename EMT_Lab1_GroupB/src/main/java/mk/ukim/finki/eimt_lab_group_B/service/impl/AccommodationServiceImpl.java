package mk.ukim.finki.eimt_lab_group_B.service.impl;

import mk.ukim.finki.eimt_lab_group_B.model.Accommodation;
import mk.ukim.finki.eimt_lab_group_B.model.Host;
import mk.ukim.finki.eimt_lab_group_B.model.dtos.AccommodationDto;
import mk.ukim.finki.eimt_lab_group_B.repository.AccommodationRepository;
import mk.ukim.finki.eimt_lab_group_B.repository.HostRepository;
import mk.ukim.finki.eimt_lab_group_B.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if(accommodation.getHostId()!=null){
            Optional<Host> host = hostRepository.findById(accommodation.getHostId());
            if (host.isPresent()) {
                Accommodation savedAccommodation = new Accommodation(accommodation.getName(), accommodation.getCategory(), host.get(), accommodation.getNumRooms());
                return Optional.of(accommodationRepository.save(savedAccommodation));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
        return accommodationRepository.findById(id).map(existingAccommodation -> {
            if (accommodation.getName()!=null){
                existingAccommodation.setName(accommodation.getName());
            }
            if (accommodation.getCategory()!=null){
                existingAccommodation.setCategory(accommodation.getCategory());
            }
            if (accommodation.getNumRooms()!=null){
                existingAccommodation.setNumRooms(accommodation.getNumRooms());
            }
            if (accommodation.getHostId()!=null){
                hostRepository.findById(accommodation.getHostId())
                        .ifPresent(existingAccommodation::setHost);
            }
            return accommodationRepository.save(existingAccommodation);
        });
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> rentAccommodation(Long id) {
        return accommodationRepository.findById(id).map(accommodation -> {
            if (accommodation.getNumRooms()>0){
                accommodation.setNumRooms(accommodation.getNumRooms()-1);
                accommodationRepository.save(accommodation);
            }
            return accommodation;
        });
    }
}

