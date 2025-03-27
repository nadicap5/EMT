package mk.ukim.finki.eimt_lab_group_B.model.dtos;

import lombok.Data;
import mk.ukim.finki.eimt_lab_group_B.model.enumerations.AccommodationCategory;

@Data
public class AccommodationDto {
    private String name;
    private AccommodationCategory category;
    private Long hostId;
    private Integer numRooms;

    public AccommodationDto(String name, AccommodationCategory category, Long hostId, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.hostId = hostId;
        this.numRooms = numRooms;
    }

    public String getName() {
        return name;
    }

    public AccommodationCategory getCategory() {
        return category;
    }

    public Long getHostId() {
        return hostId;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(AccommodationCategory category) {
        this.category = category;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}
