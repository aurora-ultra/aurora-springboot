package com.rakuten.ross.aurora.endpoint.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatResponse {
    private String id;
    private String reply;
}
