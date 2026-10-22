package com.plataforma.devflow.service;

import com.plataforma.devflow.model.SnapQuickEvaluation;
import com.plataforma.devflow.model.Snapshot;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EvaluationService {

    private final GitWebhookService gitWebhookService;
    private final Map<String, Snapshot> snapshots = new ConcurrentHashMap<>();
    private final Map<String, SnapQuickEvaluation> evaluations = new ConcurrentHashMap<>();

    public EvaluationService(GitWebhookService gitWebhookService) {
        this.gitWebhookService = gitWebhookService;
    }

    // Crea un "Snapshot" basado en la información del commit y los resultados de las pruebas
    public Snapshot createSnapshot(String repositoryName, String commitHash, int totalTests, int testsPassed, double coverage) {
        String snapshotId = "snap-" + UUID.randomUUID().toString().substring(0, 8);
        Snapshot snapshot = new Snapshot(snapshotId, repositoryName, commitHash, totalTests, testsPassed, coverage, LocalDateTime.now());
        snapshots.put(snapshotId, snapshot);
        return snapshot;
    }

    // Procesa la evaluación SnapQuick (Combina Snapshot + Calificación rápida del docente)
    public SnapQuickEvaluation submitSnapQuick(String studentName, String snapshotId, double qualitativeScore, String feedback, Map<String, Double> criteriaScores) {
        Snapshot snapshot = snapshots.get(snapshotId);
        
        // Cálculo automático basado en % de tests pasados en el Snapshot
        double testSuccessRatio = snapshot != null && snapshot.totalTestsExecuted() > 0 
                ? (double) snapshot.testsPassed() / snapshot.totalTestsExecuted() 
                : 1.0;
        
        double automatedScore = testSuccessRatio * 5.0; // Escala 0.0 - 5.0
        
        // Ponderación: 60% automático (Snapshot) + 40% docente (SnapQuick)
        double finalGrade = Math.round(((automatedScore * 0.6) + (qualitativeScore * 0.4)) * 100.0) / 100.0;

        String evalId = "eval-" + UUID.randomUUID().toString().substring(0, 8);
        SnapQuickEvaluation evaluation = new SnapQuickEvaluation(
                evalId, studentName, snapshotId, automatedScore, qualitativeScore, finalGrade, criteriaScores, feedback, LocalDateTime.now()
        );

        evaluations.put(evalId, evaluation);
        return evaluation;
    }

    public Map<String, SnapQuickEvaluation> getAllEvaluations() {
        return evaluations;
    }
}