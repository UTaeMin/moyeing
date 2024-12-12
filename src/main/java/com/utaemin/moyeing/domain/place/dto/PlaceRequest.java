package com.utaemin.moyeing.domain.place.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public sealed interface PlaceRequest permits PlaceRequest.PlaceInfoRequest{

    record PlaceInfoRequest(
            String teamName,
            String address,
            String time
    ) implements PlaceRequest {
    }


}
