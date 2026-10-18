package com.plataforma.devflow.service;

import com.plataforma.devflow.model.WebhookPayload;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GitWebhookService {

    private final Map<String, Integer> commitCounts = new ConcurrentHashMap<>();

    public void processPushEvent(WebhookPayload payload) {
        if (payload.commits() == null || payload.commits().isEmpty()) {
            return;
        }

        payload.commits().forEach(commit -> {
            String student = commit.authorName();
            commitCounts.merge(student, 1, Integer::sum);
        });
    }

    public Map<String, Integer> getCommitCounts() {
        return commitCounts;
    }
}