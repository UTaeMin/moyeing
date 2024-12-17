package com.utaemin.moyeing.domain.place.dto;



public sealed interface PlaceRequest permits PlaceRequest.PlaceInfoRequest{

    record PlaceInfoRequest(
            String teamName,
            String address,
            String time
    ) implements PlaceRequest {
    }


}
