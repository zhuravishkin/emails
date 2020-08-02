package com.zhuravishkin.springbootoracledbplsqlprocedure.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RespondingServerException extends RuntimeException {
    private final String serviceName;
    private final Integer errorCode;
    private final String userMessage;
    private final String errorDetail;

    @Builder
    public RespondingServerException(String serviceName, Integer errorCode, String userMessage, String errorDetail) {
        this.serviceName = serviceName;
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.errorDetail = errorDetail;
    }

    public ErrorResponse toResponse() {
        return new ErrorResponse(serviceName, errorCode, userMessage, errorDetail);
    }
}
