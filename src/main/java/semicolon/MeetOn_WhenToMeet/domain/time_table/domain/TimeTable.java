package semicolon.MeetOn_WhenToMeet.domain.time_table.domain;

import jakarta.persistence.*;
import lombok.Getter;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain.WhenToMeet;

@Getter
@Entity
public class TimeTable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timeBlock;
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "when_to_meet_id")
    private WhenToMeet whenToMeet;
}
