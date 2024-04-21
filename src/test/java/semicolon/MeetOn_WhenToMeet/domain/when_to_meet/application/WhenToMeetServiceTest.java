package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.application;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockCookie;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_WhenToMeet.domain.time_table.domain.TimeTable;
import semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dao.WhenToMeetRepository;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain.WhenToMeet;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto.WhenToMeetDto;
import semicolon.MeetOn_WhenToMeet.global.util.CookieUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto.*;
import static semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto.WhenToMeetDto.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
class WhenToMeetServiceTest {

    @Autowired
    WhenToMeetService whenToMeetService;

    @MockBean
    WhenToMeetRepository whenToMeetRepository;

    @MockBean
    WhenToMeetChannelService whenToMeetChannelService;

    @MockBean
    CookieUtil cookieUtil;

    MockHttpServletResponse response;
    MockHttpServletRequest request;

    @BeforeEach
    void init() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Bearer test-token");
    }

    @Test
    void 웬투밋_저장() {
        //given
        Long channelId = 1L;
        WhenToMeetSaveRequestDto whenToMeetSaveRequestDto =
                WhenToMeetSaveRequestDto.builder().eventName("test").endDate(LocalDate.now())
                        .endTime(1).startTime(1).startDate(LocalDate.now()).build();
        WhenToMeet whenToMeet = WhenToMeet.builder().id(1L).build();

        //when
        when(cookieUtil.getCookieValue("channelId", request))
                .thenReturn(String.valueOf(channelId));
        when(whenToMeetChannelService.channelExists(channelId, request.getHeader("Authorization")))
                .thenReturn(true);
        try (MockedStatic<WhenToMeet> mockedStatic = Mockito.mockStatic(WhenToMeet.class)) {
            mockedStatic.when(() -> WhenToMeet.toWhenToMeet(whenToMeetSaveRequestDto, channelId))
                    .thenReturn(whenToMeet);
            when(whenToMeetRepository.existsByChannelId(channelId))
                    .thenReturn(false);
            when(whenToMeetRepository.save(whenToMeet))
                    .thenReturn(whenToMeet);

            //then
            assertThat(whenToMeet.getId()).isEqualTo(whenToMeetService.saveWhenToMeet(whenToMeetSaveRequestDto, request));
        }
    }

    @Test
    void 웬투밋_조회() {
        //given
        Long channelId = 1L;
        WhenToMeet whenToMeet = WhenToMeet.builder().id(1L).title("test")
                .endDate(LocalDateTime.now())
                .endTime(1)
                .startDate(LocalDateTime.now())
                .startTime(2)
                .build();
        TimeTable timeTable1 = new TimeTable(1L, "test1", 1L, whenToMeet);
        TimeTable timeTable2 = new TimeTable(2L, "test2", 1L, whenToMeet);
        whenToMeet.getTimeTableList().add(timeTable1);
        whenToMeet.getTimeTableList().add(timeTable2);

        //when
        when(cookieUtil.getCookieValue("channelId", request))
                .thenReturn(String.valueOf(1L));
        when(whenToMeetRepository.findWhenToMeet(channelId))
                .thenReturn(Optional.of(whenToMeet));

        //then
        WhenToMeetResponseDto result = whenToMeetService.getWhenToMeet(request);
        assertThat(result.getTitle()).isEqualTo("test");
        assertThat(result.getTimeBlockList().size()).isEqualTo(2);
    }

    @Test
    void 웬투밋_삭제() {
        //given
        Long channelId = 1L;
        WhenToMeet whenToMeet = WhenToMeet.builder().id(1L).title("test")
                .endDate(LocalDateTime.now())
                .endTime(1)
                .startDate(LocalDateTime.now())
                .startTime(2)
                .build();
        TimeTable timeTable1 = new TimeTable(1L, "test1", 1L, whenToMeet);
        TimeTable timeTable2 = new TimeTable(2L, "test2", 1L, whenToMeet);
        whenToMeet.getTimeTableList().add(timeTable1);
        whenToMeet.getTimeTableList().add(timeTable2);

        //when
        when(cookieUtil.getCookieValue("channelId", request)).thenReturn(String.valueOf(channelId));
        when(whenToMeetRepository.findByChannelId(channelId)).thenReturn(Optional.of(whenToMeet));
        whenToMeetService.deleteWhenToMeet(request);

        //then
        verify(whenToMeetRepository).findByChannelId(channelId);
        verify(whenToMeetRepository).delete(whenToMeet);
    }
}