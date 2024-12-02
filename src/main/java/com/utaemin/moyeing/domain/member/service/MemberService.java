package com.utaemin.moyeing.domain.member.service;

import com.utaemin.moyeing.domain.member.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final Integer randomLength = 5;

    private final RedisTemplate<String, Object> redisTemplate;



    /**
     * 입력된 사람들을 레디스에 저장
     *
     * @param members 리스트 형태로 저장된 인원들
     */
    public void createMemberInfo(List<MemberRequest.MemberInfoRequest> members) {
        log.info(":::저장 시작:::");
        String teamKey = "Team:"+generateRandomString();
        for(MemberRequest.MemberInfoRequest val : members){
            redisTemplate.opsForList().rightPush(teamKey, val);
        }

        log.info(":::저장 끝:::");
    }

    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < randomLength; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }
}
