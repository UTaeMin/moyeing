package com.utaemin.moyeing.domain.member.dto;

import java.util.List;

public sealed interface MemberRequest permits MemberRequest.MemberInfoRequest, MemberRequest.MemberInfoListRequest {

    // 단일 회원 정보
    record MemberInfoRequest(
            String name,
            String address
    ) implements MemberRequest {
    }

    // 여러 회원 정보를 포함하는 요청
    record MemberInfoListRequest(
            List<MemberInfoRequest> members
    ) implements MemberRequest {
    }
}
