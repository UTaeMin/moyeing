package com.utaemin.moyeing.domain.member.controller;

import com.utaemin.moyeing.domain.member.dto.MemberRequest;
import com.utaemin.moyeing.domain.member.dto.MemberResponse;
import com.utaemin.moyeing.domain.member.service.MemberService;
import com.utaemin.moyeing.response.ApiResponse;
import com.utaemin.moyeing.response.ApiResponseMemberEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponse.MemberInfoResponse>> createMemberInfo(@RequestBody MemberRequest.MemberInfoListRequest MemberInfo){
        List<MemberRequest.MemberInfoRequest> members = MemberInfo.members();
        MemberResponse.MemberInfoResponse teamName = memberService.createMemberInfo(members);
        return ApiResponse.of(ApiResponse.of(ApiResponseMemberEnum.MEMBER_CREATE_SUCCESS,teamName));
    }



}
