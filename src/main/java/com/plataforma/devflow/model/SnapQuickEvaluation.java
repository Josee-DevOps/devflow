package com.plataforma.devflow.model;

import java.time.LocalDateTime;
import java.util.Map;

public record SnapQuickEvaluation(
    String evaluationId,
    String studentName,
    String snapshotId,
    double automatedScore,    // Calculado por el Snapshot
    double qualitativeScore,  // Asignado rápido por el profesor
    double finalGrade,        // Nota consolidada
    Map<String, Double> criteriaScores, // Puntaje por ID de criterio
    String teacherFeedback,
    LocalDateTime evaluatedAt
) {}