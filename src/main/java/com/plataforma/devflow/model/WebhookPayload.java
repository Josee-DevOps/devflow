package com.plataforma.devflow.model;

import java.util.List;

public record WebhookPayload(
    String repositoryName,
    String branch,
    String sender,
    List<CommitData> commits
) {}