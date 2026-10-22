package com.plataforma.devflow.controller;

import com.plataforma.devflow.model.SnapQuickEvaluation;
import com.plataforma.devflow.model.Snapshot;
import com.plataforma.devflow.service.EvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // Endpoint para tomar un Snapshot técnico
    @PostMapping("/snapshot")
    public ResponseEntity<Snapshot> createSnapshot(
            @RequestParam String repo,
            @RequestParam String commit,
            @RequestParam int totalTests,
            @RequestParam int testsPassed,
            @RequestParam double coverage) {
        return ResponseEntity.ok(evaluationService.createSnapshot(repo, commit, totalTests, testsPassed, coverage));
    }

    // Endpoint SnapQuick para que el docente califique en 1 clic
    @PostMapping("/snap-quick")
    public ResponseEntity<SnapQuickEvaluation> submitSnapQuick(
            @RequestParam String studentName,
            @RequestParam String snapshotId,
            @RequestParam double qualitativeScore,
            @RequestParam(defaultValue = "Buen trabajo en general") String feedback) {
        
        Map<String, Double> sampleCriteria = Map.of(
            "CRIT-DOCS", 5.0,
            "CRIT-ARCH", qualitativeScore
        );

        return ResponseEntity.ok(evaluationService.submitSnapQuick(studentName, snapshotId, qualitativeScore, feedback, sampleCriteria));
    }

    // Obtener todas las evaluaciones registradas
    @GetMapping
    public ResponseEntity<Map<String, SnapQuickEvaluation>> getAllEvaluations() {
        return ResponseEntity.ok(evaluationService.getAllEvaluations());
    }
}