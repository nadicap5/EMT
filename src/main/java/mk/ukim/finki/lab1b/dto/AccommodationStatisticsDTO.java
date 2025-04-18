package mk.ukim.finki.lab1b.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccommodationStatisticsDTO {
    private String category;
    private Long count;
}
