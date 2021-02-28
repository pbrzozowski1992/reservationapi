package pl.sdaacademy.reservationapi.conference_room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    public List<ConferenceRoom> getConferenceRoomList() {
        return conferenceRoomRepository.findAll();
    }

    public ConferenceRoom addConferenceRoom(ConferenceRoom conferenceRoom) {
        return conferenceRoomRepository.save(conferenceRoom);
    }
}
