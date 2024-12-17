package com.utaemin.moyeing.domain.result.controller;


import com.utaemin.moyeing.domain.result.dto.ResultRequest;
import com.utaemin.moyeing.domain.result.service.ResultService;
import com.utaemin.moyeing.response.ApiResponse;
import com.utaemin.moyeing.response.ApiResponseResultEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> getResult(@RequestBody ResultRequest.ResultInfoRequest resultInfoRequest) throws Exception {
        resultService.getResult(resultInfoRequest.teamName());
        return ApiResponse.of(ApiResponse.of(ApiResponseResultEnum.RESULT_RETURN_SUCCESS));
    }





}
