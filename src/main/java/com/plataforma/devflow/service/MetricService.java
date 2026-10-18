package com.plataforma.devflow.service;

import com.plataforma.devflow.model.StudentMetric;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MetricService {

    private final GitWebhookService gitWebhookService;

    public MetricService(GitWebhookService gitWebhookService) {
        this.gitWebhookService = gitWebhookService;
    }

    public List<StudentMetric> calculateMetrics() {
        Map<String, Integer> counts = gitWebhookService.getCommitCounts();
        int totalCommits = counts.values().stream().mapToInt(Integer::intValue).sum();

        if (totalCommits == 0) {
            return Collections.emptyList();
        }

        List<StudentMetric> metrics = new ArrayList<>();
        counts.forEach((studentName, commitCount) -> {
            double percentage = ((double) commitCount / totalCommits) * 100.0;
            double roundedPercentage = Math.round(percentage * 100.0) / 100.0;
            
            String simulatedEmail = studentName.toLowerCase().replaceAll("\\s+", "") + "@uninorte.edu.co";
            metrics.add(new StudentMetric(studentName, simulatedEmail, commitCount, roundedPercentage));
        });

        return metrics;
    }
}