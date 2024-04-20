package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain.WhenToMeet;

import java.util.Optional;

public interface WhenToMeetRepository extends JpaRepository<WhenToMeet, Long> {

    @Modifying
    @Query("delete from WhenToMeet wtm where wtm.channelId = :channelId")
    int deleteWhenToMeetsByChannelId(Long channelId);

    boolean existsByChannelId(Long channelId);

    @Query("select wtm from WhenToMeet wtm left join fetch wtm.timeTableList where wtm.channelId = :channelId")
    Optional<WhenToMeet> findWhenToMeet(@Param("channelId") Long channelId);
}
