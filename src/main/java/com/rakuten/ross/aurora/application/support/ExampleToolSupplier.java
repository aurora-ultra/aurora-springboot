package com.rakuten.ross.aurora.application.support;

import com.rakuten.ross.aurora.application.ChatContext;
import com.rakuten.ross.aurora.application.ChatTool;
import com.rakuten.ross.aurora.application.ChatToolSupplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExampleToolSupplier implements ChatToolSupplier {

	private final ExampleTool exampleTool;

	@Override
	public boolean support(ChatContext context) {
		return context.getChatOption().isEnableExampleTools();
	}

	@Override
	public ChatTool getTool(ChatContext context) {
		return exampleTool;
	}
}
