package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dao.WhenToMeetRepository;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain.WhenToMeet;
import semicolon.MeetOn_WhenToMeet.global.exception.BusinessLogicException;
import semicolon.MeetOn_WhenToMeet.global.exception.code.ExceptionCode;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WhenToMeetKafkaService {

    private final WhenToMeetRepository whenToMeetRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final static String CHANNEL_DELETED_TOPIC = "channel_deleted_topic";

    @Transactional
    @KafkaListener(topics = CHANNEL_DELETED_TOPIC, groupId = "channel-group")
    public void deletedByChannelDeleted(String channelIdStr) {
        log.info("Channel 삭제 channelId={}", channelIdStr);
        Long channelId = Long.valueOf(channelIdStr);
        WhenToMeet whenToMeet = whenToMeetRepository.findByChannelId(channelId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WHENTOMEET_NOT_FOUND));
        whenToMeetRepository.delete(whenToMeet);
        log.info("WhenToMeet 삭제 완료");
    }
}
