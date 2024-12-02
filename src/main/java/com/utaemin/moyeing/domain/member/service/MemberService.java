package com.utaemin.moyeing.domain.member.service;

import com.utaemin.moyeing.domain.member.dto.MemberRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {


    public void createMemberInfo(List<MemberRequest.MemberInfoRequest> members) {
        // Redis에 넣을까 어떡하지 흠..

    }
}
