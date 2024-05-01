package semicolon.MeetOn_WhenToMeet.domain.time_table.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import semicolon.MeetOn_WhenToMeet.domain.time_table.domain.TimeTable;

public class TimeTableDto {

    @Getter
    @NoArgsConstructor
    public static class UpdateTimeTableDto {
        private String timeBlock;

        @Builder
        public UpdateTimeTableDto(String timeBlock) {
            this.timeBlock = timeBlock;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class TimeTableInfoDto{
        private Long memberId;
        private String timeBlock;

        @Builder
        public TimeTableInfoDto(Long memberId, String timeBlock) {
            this.memberId = memberId;
            this.timeBlock = timeBlock;
        }

        public static TimeTableInfoDto toTimeTableInfoDto(TimeTable timeTable) {
            return TimeTableInfoDto
                    .builder()
                    .memberId(timeTable.getMemberId())
                    .timeBlock(timeTable.getTimeBlock())
                    .build();
        }
    }
}
