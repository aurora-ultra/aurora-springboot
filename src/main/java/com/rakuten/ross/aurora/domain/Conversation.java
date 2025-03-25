package com.rakuten.ross.aurora.domain;

import java.time.LocalDateTime;
import java.util.List;
import com.rakuten.ross.aurora.core.layer.Model;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.messages.Message;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Conversation implements Model , PromptMessageCreator {

	private String id;

	private LocalDateTime startTime;

	private Agent agent;

	public static Conversation start(String id, LocalDateTime startTime) {
		var conversation = new Conversation();
		conversation.setId(id);
		conversation.setStartTime(startTime);
		return conversation;
	}

	@Override
	public List<Message> createPromptMessages() {
		return agent.createPromptMessages();
	}
}
