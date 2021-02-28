package pl.sdaacademy.reservationapi.conference_room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ConferenceRoomServiceTest {

    @TestConfiguration
    static class TestConferenceRoomServiceConfiguration {
        @Bean
        public ConferenceRoomService conferenceRoomService(ConferenceRoomRepository conferenceRoomRepository) {
            return new ConferenceRoomService(new ConferenceRoomTransformer(), conferenceRoomRepository);
        }
    }

    @MockBean
    ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    ConferenceRoomService conferenceRoomService;

    @Test
    void when_get_page_number_2_with_page_size_2_of_conference_rooms_then_2_records_should_be_returned(){
        //given
        int pageNumber = 2;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize);
        Mockito.when(conferenceRoomRepository.count()).thenReturn(4L);
        Mockito.when(conferenceRoomRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(Arrays.asList(
                        new ConferenceRoom("1"), new ConferenceRoom("2")
                )));

        //when
        Envelop<List<ConferenceRoomDTO>> data = conferenceRoomService.getConferenceRoomList(pageNumber, pageSize);

        //then
        assertEquals(2, data.getData().size());
        assertEquals(2, data.getPageNumber());
        assertEquals(2, data.getDataCount());
    }

    @Test
    void when_get_page_number_2_with_page_size_2_of_conference_rooms_list_of_size_2_then_2_records_should_be_returned_from_last_page(){
        //given
        int pageNumber = 2;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(0,pageSize);
        Mockito.when(conferenceRoomRepository.count()).thenReturn(2L);
        Mockito.when(conferenceRoomRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(Arrays.asList(
                        new ConferenceRoom("1"), new ConferenceRoom("2")
                )));

        //when
        Envelop<List<ConferenceRoomDTO>> data = conferenceRoomService.getConferenceRoomList(pageNumber, pageSize);

        //then
        assertEquals(2, data.getData().size());
        assertEquals(1, data.getPageNumber());
        assertEquals(2, data.getDataCount());
    }

}