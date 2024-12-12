package com.utaemin.moyeing.domain.place.dto;

import java.time.LocalDateTime;


public sealed interface PlaceResponse permits PlaceResponse.PlaceInfoResponse{

    record PlaceInfoResponse(
            String teamName
    ) implements PlaceResponse {
    }


}
