package com.plataforma.devflow.model;

import java.time.LocalDateTime;

public record Snapshot(
    String snapshotId,
    String repositoryName,
    String commitHash,
    int totalTestsExecuted,
    int testsPassed,
    double codeCoveragePercentage,
    LocalDateTime capturedAt
) {}