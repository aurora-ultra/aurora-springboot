package com.rakuten.ross.aurora.application.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ChatCommand {

	private final String conversationId;
	private final ChatOption option;
	private final String content;

}
