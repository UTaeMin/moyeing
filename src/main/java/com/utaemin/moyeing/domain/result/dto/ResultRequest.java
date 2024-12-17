package com.utaemin.moyeing.domain.result.dto;

public sealed interface ResultRequest permits ResultRequest.ResultInfoRequest{

    record ResultInfoRequest(
            String teamName
    ) implements ResultRequest {
    }


}
