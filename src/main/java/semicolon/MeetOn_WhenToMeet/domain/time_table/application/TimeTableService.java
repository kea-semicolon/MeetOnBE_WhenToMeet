package semicolon.MeetOn_WhenToMeet.domain.time_table.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_WhenToMeet.domain.time_table.dao.TimeTableRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
}
