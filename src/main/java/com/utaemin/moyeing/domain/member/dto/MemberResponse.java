package com.utaemin.moyeing.domain.member.dto;

import java.util.List;

public sealed interface MemberResponse permits MemberResponse.MemberInfoResponse {

    // 단일 회원 정보
    record MemberInfoResponse(
            String TeamName
    ) implements MemberResponse {
    }


}
