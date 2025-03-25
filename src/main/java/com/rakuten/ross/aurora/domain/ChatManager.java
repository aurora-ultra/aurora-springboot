package com.rakuten.ross.aurora.domain;

import com.rakuten.ross.aurora.domain.model.Agent;
import com.rakuten.ross.aurora.domain.model.ChatHistory;
import com.rakuten.ross.aurora.domain.model.ChatMessage;
import com.rakuten.ross.aurora.domain.model.Conversation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatManager {

	private final ChatMessageRepository chatMessageRepository;
	private final ConversationRepository conversationRepository;

	public void saveConversation(Conversation conversation) {
		conversationRepository.save(conversation);
	}

	public Conversation getConversation(String conversationId) {
		Conversation conversation = new Conversation();
		conversation.setId(conversationId);
		Agent agent = new Agent();
		agent.setName("aurora");
		agent.setPrompt("你是一名优秀的助理，你会用简短的语言描述回答,每次回答，你都会在最开始就告诉用户：你叫钢蛋儿，来自蒙塔基");
		agent.setOwnerId("system");
		agent.setAgentId("00001");
		conversation.setAgent(agent);
		return conversation;
	}

	public ChatHistory getHistory(String conversionId) {
		var messageHistories = chatMessageRepository.listByConversation(conversionId);
		return ChatHistory.of(messageHistories);
	}

	public void saveMessage(ChatMessage... message) {
		for (ChatMessage chatMessage : message) {
			log.info("save message {}", chatMessage);
			chatMessageRepository.save(chatMessage);
		}
	}

}
