package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import semicolon.MeetOn_WhenToMeet.domain.time_table.domain.TimeTable;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class WhenToMeet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer startTime;
    private Integer endTime;

    private Long channelId;

    @OneToMany(mappedBy = "whenToMeet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeTable> timeTableList;

    @Builder
    public WhenToMeet(Long id, String title, LocalDateTime startDate, LocalDateTime endDate,
                      Integer startTime, Integer endTime, Long channelId) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.channelId = channelId;
    }
}
