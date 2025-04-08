package com.rakuten.ross.aurora.application.support;

import com.rakuten.ross.aurora.application.ChatTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebSearchTool implements ChatTool {

	@Override
	public String getName() {
		return "WebSearchTool";
	}

	@Override
	public String getDescription() {
		return "search information from internet";
	}

	@Tool(description = "search information from internet")
	public String webExternalSearch(String search, ToolContext toolContext) {
		return """
				搜索结果：
				1. 最近，美国对全世界加收对等关税，引来全世界关注。
				2. 台湾饶舌圈“厌女”论战：女音乐人创造Sisterhood“推倒嘻哈父权高墙”。
				""";
	}


}