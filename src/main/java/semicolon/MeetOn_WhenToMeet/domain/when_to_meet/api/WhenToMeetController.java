package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    /**
     *  WhenToMeet 조회
     * @param request
     * @return
     */
    @Operation(summary = "WhenToMeet 조회", description = "WhenToMeet 조회")
    @GetMapping
    public ResponseEntity<WhenToMeetResponseDto> responseWhenToMeet(HttpServletRequest request) {
        return ResponseEntity.ok(whenToMeetService.getWhenToMeet(request));
    }

    /**
     * WhenToMeet 삭제
     * @param request
     * @return
     */
    @Operation(summary = "WhenToMeet 삭제", description = "WhenToMeet 삭제")
    @DeleteMapping
    public ResponseEntity<String> deleteWhenToMeet(HttpServletRequest request){
        whenToMeetService.deleteWhenToMeet(request);
        return ResponseEntity.ok("Ok");
    }

    /**
     * WhenToMeet 수정
     */
}
