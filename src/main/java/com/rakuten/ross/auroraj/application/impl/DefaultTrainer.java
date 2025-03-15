package com.rakuten.ross.auroraj.application.impl;

import com.rakuten.ross.auroraj.ablity.knowledge.KnowledgeManager;
import com.rakuten.ross.auroraj.application.Trainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.function.Predicate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultTrainer implements Trainer {

    private final KnowledgeManager knowledgeManager;


    @Override
    public void learn(String type) {
        var suffix = "." + type;
        var filter = (Predicate<Path>) path -> path.getFileName().endsWith(suffix);
        var root = Path.of("./material");
        knowledgeManager.learn(root, filter);
    }


}
