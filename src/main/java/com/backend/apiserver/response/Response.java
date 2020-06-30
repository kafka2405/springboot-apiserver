package com.backend.apiserver.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String code;
}
