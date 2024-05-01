package semicolon.MeetOn_WhenToMeet.domain.time_table.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.MeetOn_WhenToMeet.domain.time_table.domain.TimeTable;

import java.util.Optional;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    Optional<TimeTable> findByMemberId(Long memberId);
}
