package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
}
