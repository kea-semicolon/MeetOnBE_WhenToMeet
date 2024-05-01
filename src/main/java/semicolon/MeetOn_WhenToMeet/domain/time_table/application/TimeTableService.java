package semicolon.MeetOn_WhenToMeet.domain.time_table.application;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_WhenToMeet.domain.time_table.dao.TimeTableRepository;
import semicolon.MeetOn_WhenToMeet.domain.time_table.domain.TimeTable;
import semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto;
import semicolon.MeetOn_WhenToMeet.global.exception.BusinessLogicException;
import semicolon.MeetOn_WhenToMeet.global.exception.code.ExceptionCode;
import semicolon.MeetOn_WhenToMeet.global.util.CookieUtil;

import java.util.List;

import static semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final CookieUtil cookieUtil;

    @Transactional
    public void updateTimeTable(UpdateTimeTableDto updateTimeTableDto, HttpServletRequest request) {
        Long memberId = Long.valueOf(cookieUtil.getCookieValue("memberId", request));
        TimeTable timeTable = timeTableRepository.findByMemberId(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TIMETABLE_NOT_FOUND));
        timeTable.updateTimeTable(updateTimeTableDto.getTimeBlock());
    }
}
