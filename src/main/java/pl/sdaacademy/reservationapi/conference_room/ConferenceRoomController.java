package pl.sdaacademy.reservationapi.conference_room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferencerooms")
public class ConferenceRoomController {

    private final static int PAGE_SIZE = 10;
    private final ConferenceRoomService conferenceRoomService;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping
    public Envelop<List<ConferenceRoomDTO>> getAllConferenceRooms(@RequestParam(required = false, defaultValue = "1") int pageNumber) {
        return conferenceRoomService.getConferenceRoomList(pageNumber, PAGE_SIZE);
    }

    @PostMapping
    public ConferenceRoomDTO addConferenceRoom(@RequestBody ConferenceRoomDTO conferenceRoomDTO) {
        return conferenceRoomService.addConferenceRoom(conferenceRoomDTO);
    }
}
