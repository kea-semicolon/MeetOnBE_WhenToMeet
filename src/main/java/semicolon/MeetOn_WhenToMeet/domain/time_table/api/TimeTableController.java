package semicolon.MeetOn_WhenToMeet.domain.time_table.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.MeetOn_WhenToMeet.domain.time_table.application.TimeTableService;
import semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto;

import static semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto.*;

@Slf4j
@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class TimeTableController {

    private final TimeTableService timeTableService;

    @PatchMapping
    public ResponseEntity<String> updateTimeTable(@RequestBody UpdateTimeTableDto updateTimeTableDto,
                                                  HttpServletRequest request) {
        timeTableService.updateTimeTable(updateTimeTableDto, request);
        return ResponseEntity.ok("ok");
    }
}
