package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.application.WhenToMeetService;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto.WhenToMeetDto;

import static semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto.WhenToMeetDto.*;

@Slf4j
@RestController
@RequestMapping("/when-to-meet")
@RequiredArgsConstructor
public class WhenToMeetController {

    private final WhenToMeetService whenToMeetService;

    /**
     * WhenToMeet 생성
     * @param whenToMeetSaveRequestDto
     * @param request
     * @return
     */
    @Operation(summary = "WhenToMeet 생성", description = "WhenToMeet 생성 + WhenToMeetSaveRequestDto")
    @PostMapping
    public ResponseEntity<String> saveWhenToMeet(@RequestBody WhenToMeetSaveRequestDto whenToMeetSaveRequestDto,
                                                 HttpServletRequest request) {
        Long saveId = whenToMeetService.saveWhenToMeet(whenToMeetSaveRequestDto, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveId + " Created");
    }
}
