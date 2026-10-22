package com.plataforma.devflow.model;

public record RubricCriterion(
    String id,
    String name,
    String description,
    double maxScore,
    boolean isAutomated // true para tests/commits (Snapshot), false para cualitativo (SnapQuick)
) {}