package com.zhuravishkin.springbootoracledbplsqlprocedure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ErrorResponse {
    @JsonProperty("errorDescription")
    private final ErrorDescription errorDescription;

    public ErrorResponse(final String serviceName, final Integer errorCode, final String userMessage, final String errorDetail) {
        this.errorDescription = new ErrorDescription(serviceName, errorCode, userMessage, errorDetail);
    }

    @AllArgsConstructor
    @Getter
    private static class ErrorDescription {
        private final String serviceName;
        private final Integer errorCode;
        private final String userMessage;
        private final String errorDetail;
    }
}
