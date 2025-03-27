package mk.ukim.finki.eimt_lab_group_B.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.eimt_lab_group_B.model.enumerations.AccommodationCategory;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Entity
@NoArgsConstructor
public class Accommodation {
    //    id (Long), name (String), category (enum), host (Host), numRooms (Integer).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private AccommodationCategory category;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Host host;
    private Integer numRooms;

    public Accommodation(String name, AccommodationCategory category, Host host, Integer numRooms, Long id) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.id = id;
    }

    public Accommodation(String name, AccommodationCategory category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccommodationCategory getCategory() {
        return category;
    }

    public void setCategory(AccommodationCategory category) {
        this.category = category;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}
