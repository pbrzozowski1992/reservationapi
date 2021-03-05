package pl.sdaacademy.reservationapi.conference_room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Envelop<List<ConferenceRoomDTO>> getConferenceRoomList(int pageNumber, int pageSize) {
        long dataCount = conferenceRoomRepository.count();
        long lastPage = dataCount/pageSize;
        if (pageNumber > lastPage) {
            pageNumber = (int)lastPage;
        }
        Pageable pageable = PageRequest.of(pageNumber <= 0 ? 0 : pageNumber - 1, pageSize);
        List<ConferenceRoomDTO> conferenceRoomDTOS = conferenceRoomRepository.findAll(pageable).stream()
                .map(conferenceRoomTransformer::toDTO)
                .collect(Collectors.toList());
        return new Envelop<>(pageNumber, conferenceRoomDTOS.size(), conferenceRoomDTOS);
    }

    public ConferenceRoomDTO addConferenceRoom(ConferenceRoomDTO conferenceRoomDTO) {
        ConferenceRoom conferenceRoom = conferenceRoomTransformer.toEntity(conferenceRoomDTO);
        return conferenceRoomTransformer.toDTO(conferenceRoomRepository.save(conferenceRoom));
    }
}
