package com.utaemin.moyeing.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiResponsePlaceEnum implements ApiResponseEnum {
    // 200
    PLACE_SAVE_SUCCESS(HttpStatus.OK,"장소 등록에 성공하였습니다."),

    // 400
    MEMBER_COUNT_WRONG(HttpStatus.BAD_REQUEST,"10명 이하만 가능합니다.");



    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ApiResponsePlaceEnum(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        code = httpStatus.value();
    }
}
