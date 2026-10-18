package com.plataforma.devflow.controller;

import com.plataforma.devflow.model.WebhookPayload;
import com.plataforma.devflow.service.GitWebhookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/git")
public class GitWebhookController {

    private final GitWebhookService gitWebhookService;

    public GitWebhookController(GitWebhookService gitWebhookService) {
        this.gitWebhookService = gitWebhookService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> receiveWebhook(@RequestBody WebhookPayload payload) {
        gitWebhookService.processPushEvent(payload);
        return ResponseEntity.ok("Procesamiento del webhook recibido");
    }
}