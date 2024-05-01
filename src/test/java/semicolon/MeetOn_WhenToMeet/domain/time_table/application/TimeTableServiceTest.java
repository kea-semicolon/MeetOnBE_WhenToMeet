package semicolon.MeetOn_WhenToMeet.domain.time_table.application;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_WhenToMeet.domain.time_table.dao.TimeTableRepository;
import semicolon.MeetOn_WhenToMeet.domain.time_table.domain.TimeTable;
import semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.application.WhenToMeetChannelService;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.application.WhenToMeetService;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dao.WhenToMeetRepository;
import semicolon.MeetOn_WhenToMeet.global.util.CookieUtil;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
class TimeTableServiceTest {
    @Autowired
    TimeTableService timeTableService;

    @Autowired
    TimeTableRepository timeTableRepository_real;

    @MockBean
    TimeTableRepository timeTableRepository;

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
    void TimeTable_수정() {
        //given
        Long memberId = 1L;
        UpdateTimeTableDto updateTimeTableDto = new UpdateTimeTableDto("test");
        TimeTable timeTable = TimeTable.builder().id(1L).timeBlock("tst").build();

        //when
        when(cookieUtil.getCookieValue("memberId", request)).thenReturn(String.valueOf(memberId));
        when(timeTableRepository.findByMemberId(memberId)).thenReturn(Optional.ofNullable(timeTable));
        timeTableService.updateTimeTable(updateTimeTableDto, request);

        //then
        assertThat(timeTable.getTimeBlock()).isEqualTo(updateTimeTableDto.getTimeBlock());
    }
}