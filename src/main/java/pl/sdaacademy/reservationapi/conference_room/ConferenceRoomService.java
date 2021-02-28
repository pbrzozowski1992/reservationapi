package pl.sdaacademy.reservationapi.conference_room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomTransformer conferenceRoomTransformer;

    @Autowired
    public ConferenceRoomService(ConferenceRoomTransformer conferenceRoomTransformer,
                                 ConferenceRoomRepository conferenceRoomRepository) {
        this.conferenceRoomTransformer = conferenceRoomTransformer;
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    public List<ConferenceRoomDTO> getConferenceRoomList() {
        return conferenceRoomRepository.findAll().stream()
                .map(conferenceRoomTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public ConferenceRoomDTO addConferenceRoom(ConferenceRoomDTO conferenceRoomDTO) {
        ConferenceRoom conferenceRoom = conferenceRoomTransformer.toEntity(conferenceRoomDTO);
        return conferenceRoomTransformer.toDTO(conferenceRoomRepository.save(conferenceRoom));
    }
}
