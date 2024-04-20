package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain.WhenToMeet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static semicolon.MeetOn_WhenToMeet.domain.time_table.dto.TimeTableDto.*;

public class WhenToMeetDto {

    @Getter
    @NoArgsConstructor
    public static class WhenToMeetSaveRequestDto {
        private String eventName;
        private LocalDate startDate; // LocalDate 타입으로 변경
        private LocalDate endDate; // LocalDate 타입으로 변경
        private int startTime;
        private int endTime;

        @Builder
        public WhenToMeetSaveRequestDto(String eventName, LocalDate startDate, LocalDate endDate, int startTime, int endTime) {
            this.eventName = eventName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        // LocalDateTime으로 변환하는 메소드 추가
        public LocalDateTime getStartDateTime() {
            return LocalDateTime.of(startDate, LocalTime.of(startTime, 0));
        }

        public LocalDateTime getEndDateTime() {
            return LocalDateTime.of(endDate, LocalTime.of(endTime, 0));
        }
    }

    @Getter
    @NoArgsConstructor
    public static class WhenToMeetResponseDto {
        private String title;
        private int startTime;
        private LocalDate startDate;
        private int endTime;
        private LocalDate endDate;
        private List<TimeTableInfoDto> timeBlockList = new ArrayList<>();

        @Builder
        public WhenToMeetResponseDto(String title, int startTime, LocalDate startDate, int endTime,
                                     LocalDate endDate, List<TimeTableInfoDto> timeBlockList) {
            this.title = title;
            this.startTime = startTime;
            this.startDate = startDate;
            this.endTime = endTime;
            this.endDate = endDate;
            this.timeBlockList = timeBlockList;
        }

        public static WhenToMeetResponseDto toWhenToMeetResponseDto(WhenToMeet whenToMeet) {
            List<TimeTableInfoDto> result = new ArrayList<>();
            result = whenToMeet.getTimeTableList().stream().map(TimeTableInfoDto::toTimeTableInfoDto).toList();
            return WhenToMeetResponseDto
                    .builder()
                    .title(whenToMeet.getTitle())
                    .startTime(whenToMeet.getStartTime())
                    .startDate(LocalDate.from(whenToMeet.getStartDate()))
                    .endTime(whenToMeet.getEndTime())
                    .endDate(LocalDate.from(whenToMeet.getStartDate()))
                    .timeBlockList(result)
                    .build();
        }
    }
}
