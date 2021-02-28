package pl.sdaacademy.reservationapi.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationDTO {
    private String name;
    private String description;
    private List<OrganizationConferenceRoomDTO> conferenceRooms = new ArrayList<>();

    public OrganizationDTO() {

    }

    public OrganizationDTO(String name, String description) {
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

    public List<OrganizationConferenceRoomDTO> getConferenceRooms() {
        return conferenceRooms;
    }

    public void setConferenceRooms(List<OrganizationConferenceRoomDTO> conferenceRooms) {
        this.conferenceRooms = conferenceRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationDTO that = (OrganizationDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(conferenceRooms, that.conferenceRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, conferenceRooms);
    }
}
