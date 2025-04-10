package com.rakuten.ross.aurora.endpoint.dto;

import java.time.LocalDateTime;
import com.rakuten.ross.aurora.core.layer.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Schema(name = "Conversation")
@Accessors(chain = true)
public class ConversationDto implements Dto {

	@Schema(description = "The unique identifier of the conversation", example = "123456")
	private String id;

	@Schema(description = "The time the conversation started", example = "2021-01-01T00:00:00")
	private LocalDateTime startTime;

}
