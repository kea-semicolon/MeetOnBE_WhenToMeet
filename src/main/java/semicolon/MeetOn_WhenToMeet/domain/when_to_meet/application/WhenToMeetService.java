package semicolon.MeetOn_WhenToMeet.domain.when_to_meet.application;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dao.WhenToMeetRepository;
import semicolon.MeetOn_WhenToMeet.domain.when_to_meet.domain.WhenToMeet;
import semicolon.MeetOn_WhenToMeet.global.exception.BusinessLogicException;
import semicolon.MeetOn_WhenToMeet.global.exception.code.ExceptionCode;
import semicolon.MeetOn_WhenToMeet.global.util.CookieUtil;

import static semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto.WhenToMeetDto.*;
import static semicolon.MeetOn_WhenToMeet.domain.when_to_meet.dto.WhenToMeetDto.WhenToMeetResponseDto.toWhenToMeetResponseDto;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WhenToMeetService {

    private final WhenToMeetRepository whenToMeetRepository;
    private final CookieUtil cookieUtil;
    private final WhenToMeetChannelService whenToMeetChannelService;

    @Transactional
    public Long saveWhenToMeet(WhenToMeetSaveRequestDto whenToMeetSaveRequestDto,
                               HttpServletRequest request) {
        Long channelId = Long.valueOf(cookieUtil.getCookieValue("channelId", request));
        if (!whenToMeetChannelService.channelExists(channelId, request.getHeader("Authorization"))) {
            throw new BusinessLogicException(ExceptionCode.CHANNEL_NOT_FOUND);
        }
        if(!whenToMeetRepository.existsByChannelId(channelId)){
            WhenToMeet whenToMeet = WhenToMeet.toWhenToMeet(whenToMeetSaveRequestDto, channelId);
            WhenToMeet saved = whenToMeetRepository.save(whenToMeet);
            return saved.getId();
        }
        else{
            throw new BusinessLogicException(ExceptionCode.WHENTOMEET_ALREADY_EXIST);
        }
    }


    public WhenToMeetResponseDto getWhenToMeet(HttpServletRequest request) {
        Long channelId = Long.valueOf(cookieUtil.getCookieValue("channelId", request));
        WhenToMeet whenToMeet = whenToMeetRepository.findWhenToMeet(channelId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WHENTOMEET_NOT_FOUND));
        return toWhenToMeetResponseDto(whenToMeet);
    }
}
