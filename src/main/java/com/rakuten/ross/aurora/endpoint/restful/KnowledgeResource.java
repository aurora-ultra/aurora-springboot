package com.rakuten.ross.aurora.endpoint.restful;

import com.rakuten.ross.aurora.application.KnowledgeService;
import com.rakuten.ross.aurora.endpoint.request.knowledgeLearnRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "knowledge")
@RequestMapping("/knowledge")
@RestController
@RequiredArgsConstructor
public class KnowledgeResource {

	private final KnowledgeService knowledgeService;

	@PostMapping("/tasks/learn")
	public void learn(@RequestBody knowledgeLearnRequest knowledgeLearnRequest) {
		knowledgeService.learn(knowledgeLearnRequest.getFileType());
	}

}
