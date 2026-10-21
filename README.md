# DevFlow — Backend Engine

DevFlow es un motor de backend desarrollado en **Java 21 con Spring Boot** (basado en arquitectura limpia y patrón MVC) diseñado para auditar, procesar y calcular métricas de colaboración individual en proyectos grupales de software a partir de eventos de control de versiones (Webhooks de Git).

##  Características actuales (Backend)

-   **Recepción de Webhooks de Git:** Endpoint REST (`/api/v1/git/webhook`) para la ingesta automatizada de payloads en formato JSON.
    
-   **Procesamiento de Métricas e Idempotencia:** Filtrado de commits únicos mediante hashing SHA para evitar duplicidad en reenvíos y cálculo dinámico de porcentajes de contribución por estudiante.
    
-   **API REST de Métricas:** Endpoint (`/api/v1/metrics`) para la consulta consolidada de métricas de participación en tiempo real.
