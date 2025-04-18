package mk.ukim.finki.lab1b.service.application.impl;

import mk.ukim.finki.lab1b.dto.AccommodationStatisticsDTO;
import mk.ukim.finki.lab1b.dto.CreateAccommodationDto;
import mk.ukim.finki.lab1b.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab1b.model.enumerations.Category;
import mk.ukim.finki.lab1b.repository.AccommodationRepository;
import mk.ukim.finki.lab1b.service.application.AccommodationApplicationService;
import mk.ukim.finki.lab1b.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final AccommodationRepository accommodationRepository;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, AccommodationRepository accommodationRepository) {
        this.accommodationService = accommodationService;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll().stream()
                .map(DisplayAccommodationDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation) {
        return accommodationService.save(accommodation.toAccommodation())
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        return accommodationService.update(id,accommodation.toAccommodation())
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> rentRoom(Long id) {
        return accommodationService.rentRoom(id)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public List<AccommodationStatisticsDTO> getAccommodationStatistics() {
        List<AccommodationStatisticsDTO> stats = new ArrayList<>();
        for (Category category : Category.values()) {
            long count = accommodationRepository.countByCategory(category);
            stats.add(new AccommodationStatisticsDTO(category.name(), count));
        }
        return stats;
    }
}
