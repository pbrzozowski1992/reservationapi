package pl.sdaacademy.reservationapi.organization;

import org.springframework.stereotype.Component;
import pl.sdaacademy.reservationapi.conference_room.ConferenceRoom;

@Component
public class OrganizationConferenceRoomTransformer {

    public OrganizationConferenceRoomDTO toDTO(ConferenceRoom conferenceRoom) {
        OrganizationConferenceRoomDTO organizationConferenceRoomDTO = new OrganizationConferenceRoomDTO();
        organizationConferenceRoomDTO.setName(conferenceRoom.getName());
        organizationConferenceRoomDTO.setRoomNumber(conferenceRoom.getRoomNumber());
        organizationConferenceRoomDTO.setAvailable(conferenceRoom.isAvailable());
        organizationConferenceRoomDTO.setLevel(conferenceRoom.getLevel());
        organizationConferenceRoomDTO.setCapacity(conferenceRoom.getCapacity());
        return organizationConferenceRoomDTO;
    }

    public ConferenceRoom toEntity(OrganizationConferenceRoomDTO organizationConferenceRoomDTO) {
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setName(organizationConferenceRoomDTO.getName());
        conferenceRoom.setRoomNumber(organizationConferenceRoomDTO.getRoomNumber());
        conferenceRoom.setLevel(organizationConferenceRoomDTO.getLevel());
        conferenceRoom.setCapacity(organizationConferenceRoomDTO.getCapacity());
        conferenceRoom.setAvailable(organizationConferenceRoomDTO.isAvailable());
        return conferenceRoom;
    }
}
