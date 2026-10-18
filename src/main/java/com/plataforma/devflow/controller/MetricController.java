package com.plataforma.devflow.controller;

import com.plataforma.devflow.model.StudentMetric;
import com.plataforma.devflow.service.MetricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping
    public ResponseEntity<List<StudentMetric>> getMetrics() {
        return ResponseEntity.ok(metricService.calculateMetrics());
    }
}