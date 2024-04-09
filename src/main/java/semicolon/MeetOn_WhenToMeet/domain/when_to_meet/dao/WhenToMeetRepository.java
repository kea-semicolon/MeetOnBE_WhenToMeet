package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain.WhenToMeet;

public interface WhenToMeetRepository extends JpaRepository<WhenToMeet, Long> {

    @Modifying
    @Query("delete from WhenToMeet wtm where wtm.channelId = :channelId")
    int deleteWhenToMeetsByChannelId(Long channelId);
}
