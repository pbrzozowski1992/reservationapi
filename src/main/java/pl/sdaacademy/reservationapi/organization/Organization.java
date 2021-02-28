package pl.sdaacademy.reservationapi.organization;

import pl.sdaacademy.reservationapi.conference_room.ConferenceRoom;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Organization {

    @Id
    private String name;
    private String description;

    @OneToMany(mappedBy = "organization")
    private List<ConferenceRoom> conferenceRooms;

    public Organization() {
    }

    public Organization(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
