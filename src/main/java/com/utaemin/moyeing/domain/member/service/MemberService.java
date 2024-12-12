package com.utaemin.moyeing.domain.member.service;

import com.utaemin.moyeing.domain.member.dto.MemberRequest;
import com.utaemin.moyeing.domain.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final RedisTemplate<String, Object> redisTemplate;


    /**
     * 입력된 사람들을 레디스에 저장
     *
     * @param members 리스트 형태로 저장된 인원들
     * @return 소속 팀명 (구분을 위함)
     */
    public MemberResponse.MemberInfoResponse createMemberInfo(List<MemberRequest.MemberInfoRequest> members) {
        log.info(":::사람 정보 저장 시작:::");

        String teamKey;
        do {
            teamKey = "Team:" + generateRandomString();
        } while (redisTemplate.hasKey(teamKey));

        for(MemberRequest.MemberInfoRequest val : members){
            redisTemplate.opsForList().rightPush(teamKey, val);
        }

        redisTemplate.expire(teamKey, Duration.ofMinutes(30));


        log.info(":::사람 정보 저장 끝:::");

        return new MemberResponse.MemberInfoResponse(teamKey.substring(5));
    }

    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        int len = 5;
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }
}
