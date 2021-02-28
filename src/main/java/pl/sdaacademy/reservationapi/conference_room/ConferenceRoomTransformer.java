package pl.sdaacademy.reservationapi.conference_room;

import org.springframework.stereotype.Component;
import pl.sdaacademy.reservationapi.organization.Organization;

@Component
public class ConferenceRoomTransformer {

    public ConferenceRoomDTO toDTO(ConferenceRoom conferenceRoom) {
        ConferenceRoomDTO conferenceRoomDTO = new ConferenceRoomDTO();
        conferenceRoomDTO.setName(conferenceRoom.getName());
        conferenceRoomDTO.setRoomNumber(conferenceRoom.getRoomNumber());
        conferenceRoomDTO.setAvailable(conferenceRoom.isAvailable());
        conferenceRoomDTO.setCapacity(conferenceRoom.getCapacity());
        conferenceRoomDTO.setLevel(conferenceRoom.getLevel());
        conferenceRoomDTO.setOrganizationName(conferenceRoom.getOrganization().getName());
        return conferenceRoomDTO;
    }

    public ConferenceRoom toEntity(ConferenceRoomDTO conferenceRoomDTO) {
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setName(conferenceRoomDTO.getName());
        conferenceRoom.setRoomNumber(conferenceRoomDTO.getRoomNumber());
        conferenceRoom.setAvailable(conferenceRoomDTO.isAvailable());
        conferenceRoom.setCapacity(conferenceRoomDTO.getCapacity());
        conferenceRoom.setLevel(conferenceRoomDTO.getLevel());
        Organization organization = new Organization();
        organization.setName(conferenceRoomDTO.getOrganizationName());
        conferenceRoom.setOrganization(organization);
        return conferenceRoom;
    }
}
