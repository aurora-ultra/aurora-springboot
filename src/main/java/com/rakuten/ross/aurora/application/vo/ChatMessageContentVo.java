package com.rakuten.ross.aurora.application.vo;

import com.rakuten.ross.aurora.core.layer.Vo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "ChatMessageContent")
public class ChatMessageContentVo implements Vo {

    @Schema(description = "The text of the message", example = "Hello!")
    @NotBlank
    private String text;

}
