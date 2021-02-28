package pl.sdaacademy.reservationapi.conference_room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferencerooms")
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping
    public List<ConferenceRoom> getAllConferenceRooms() {
        return conferenceRoomService.getConferenceRoomList();
    }

    @PostMapping
    public ConferenceRoom addConferenceRoom(@RequestBody ConferenceRoom conferenceRoom) {
        return conferenceRoomService.addConferenceRoom(conferenceRoom);
    }
}
