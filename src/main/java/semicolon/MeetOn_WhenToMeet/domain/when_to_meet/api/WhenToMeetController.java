package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.application.WhenToMeetService;

@Slf4j
@RestController
@RequestMapping("/when-to-meet")
@RequiredArgsConstructor
public class WhenToMeetController {

    private final WhenToMeetService whenToMeetService;
}
