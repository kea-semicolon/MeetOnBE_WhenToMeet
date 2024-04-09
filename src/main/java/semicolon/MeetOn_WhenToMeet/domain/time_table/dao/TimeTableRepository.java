package semicolon.MeetOn_WhenToMeet.domain.time_table.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.MeetOn_WhenToMeet.domain.time_table.domain.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}
