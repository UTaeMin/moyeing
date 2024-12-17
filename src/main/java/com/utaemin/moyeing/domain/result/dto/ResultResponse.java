package com.utaemin.moyeing.domain.result.dto;


import com.utaemin.moyeing.domain.place.dto.PlaceResponse;

public sealed interface ResultResponse permits ResultResponse.PlaceInfoResponse{

    record PlaceInfoResponse(

    ) implements ResultResponse {
    }


}
