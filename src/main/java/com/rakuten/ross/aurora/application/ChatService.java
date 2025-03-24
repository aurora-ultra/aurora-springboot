package com.rakuten.ross.aurora.application;

import com.rakuten.ross.aurora.application.command.ChatCommand;
import com.rakuten.ross.aurora.application.support.TimeProvider;
import com.rakuten.ross.aurora.domain.AroChatManager;
import com.rakuten.ross.aurora.domain.AroMessage;
import com.rakuten.ross.aurora.domain.AroMessageContent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatModel chatModel;
    private final TimeProvider timeProvider;
    private final AroChatManager aroChatManager;
    private final RedisVectorStore vectorStore;

    // todo stream reply
    public AroMessage chat(ChatCommand command) {
        var userMessage = AroMessage.user()
            .messageId(UUID.randomUUID().toString())
            .conversationId(command.getConversationId())
            .sendTime(timeProvider.now())
            .content(command.getContents())
            .build();

        // todo can we use spring to implement the history or replace with summary?
        var chatHistory = aroChatManager.getHistory(command.getConversationId());

        var advisors = getAdvisors(command);

        var reply = creatChatClient()
            .prompt()
            .advisors(advisors)
            .messages(chatHistory.toMessages(5))
            .messages(userMessage.toCompletionMessages())
            .call()
            .content();

        var assistantMessage = AroMessage.assistant()
            .sendTime(timeProvider.now())
            .content(List.of(AroMessageContent.of(reply)))
            .build();

        aroChatManager.saveMessage(userMessage, assistantMessage);

        return assistantMessage;
    }

    private ArrayList<Advisor> getAdvisors(ChatCommand command) {
        var advisors = new ArrayList<Advisor>();
        if (command.isSearch()) {
            advisors.add(getQuestionAnswerAdvisor());
        }
        return advisors;
    }

    private Advisor getQuestionAnswerAdvisor() {
        return new QuestionAnswerAdvisor(
            vectorStore,
            SearchRequest.builder()
                .topK(2)
                .build(),
            """
                上下文信息如下，用 --------------------- 包围
                
                ---------------------
                {question_answer_context}
                ---------------------
                
                根据上下文和提供的历史信息（而非先验知识）回复用户评论。如果答案不在上下文中，请告知用户你无法回答该问题。
                """
        );
    }

    private ChatClient creatChatClient() {
        return ChatClient.builder(chatModel)
            .build();
    }

}
