package com.plataforma.devflow.model;

import java.time.LocalDateTime;

public record CommitData(
    String commitId,
    String authorEmail,
    String authorName,
    String message,
    LocalDateTime timestamp
) {}