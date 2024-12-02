package com.utaemin.moyeing.domain.place.dto;

import java.time.LocalDateTime;


public sealed interface PlaceRequest permits PlaceRequest.PlaceInfoRequest{

    record PlaceInfoRequest(
            String teamName,
            String address,
            LocalDateTime time
    ) implements PlaceRequest {
    }


}
