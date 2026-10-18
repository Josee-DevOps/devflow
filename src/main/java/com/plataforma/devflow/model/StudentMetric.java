package com.plataforma.devflow.model;

public record StudentMetric(
    String studentName,
    String email,
    int totalCommits,
    double contributionPercentage
) {}