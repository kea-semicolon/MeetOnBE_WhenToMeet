package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import semicolon.MeetOn_WhenToMeet.domain.BaseTimeEntity;
import semicolon.MeetOn_WhenToMeet.domain.time_table.domain.TimeTable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto.WhenToMeetDto.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
public class WhenToMeet extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer startTime;
    private Integer endTime;

    private Long channelId;

    @Builder.Default
    @OneToMany(mappedBy = "whenToMeet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeTable> timeTableList = new ArrayList<>();

    @Builder
    public WhenToMeet(Long id, String title, LocalDateTime startDate, LocalDateTime endDate,
                      Integer startTime, Integer endTime, Long channelId, List<TimeTable> timeTableList) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.channelId = channelId;
        this.timeTableList = timeTableList;
    }

    public static WhenToMeet toWhenToMeet(WhenToMeetSaveRequestDto whenToMeetSaveRequestDto, Long channelId) {
        return WhenToMeet
                .builder()
                .title(whenToMeetSaveRequestDto.getEventName())
                .startDate(whenToMeetSaveRequestDto.getStartDate().atStartOfDay())
                .endDate(whenToMeetSaveRequestDto.getEndDate().atStartOfDay())
                .startTime(whenToMeetSaveRequestDto.getStartTime())
                .endTime(whenToMeetSaveRequestDto.getEndTime())
                .channelId(channelId)
                .build();
    }
}
