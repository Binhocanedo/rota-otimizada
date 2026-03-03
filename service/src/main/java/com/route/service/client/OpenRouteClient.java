package com.route.service.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class OpenRouteClient {

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${openroute.api.key}")
    private String apiKey;

    private static final String MATRIX_URL =
            "https://api.openrouteservice.org/v2/matrix/driving-car";

    public OpenRouteClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double[][] getDistanceMatrix(List<double[]> coordinates) {

        if (coordinates == null || coordinates.isEmpty()) {
            throw new IllegalArgumentException("Lista de coordenadas vazia.");
        }

        int size = coordinates.size();
        double[][] matrix = new double[size][size];

        // ===== Monta body da requisição =====
        Map<String, Object> body = new HashMap<>();
        body.put("locations", coordinates);
        body.put("metrics", List.of("distance"));
        body.put("units", "m");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", apiKey);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(
                        MATRIX_URL,
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

        // ===== VALIDAÇÕES IMPORTANTES =====
        if (response == null || response.getBody() == null) {
            throw new IllegalStateException("Resposta nula da OpenRoute API.");
        }

        Map<String, Object> responseBody = response.getBody();

        if (!responseBody.containsKey("distances")) {
            throw new IllegalStateException(
                    "Resposta da OpenRoute não contém 'distances'. Body: " + responseBody
            );
        }

        List<List<Double>> distances =
                (List<List<Double>>) responseBody.get("distances");

        if (distances == null || distances.isEmpty()) {
            throw new IllegalStateException(
                    "Matriz de distâncias vazia retornada pela OpenRoute."
            );
        }

        // ===== MONTA MATRIZ COM VALIDAÇÃO =====
        for (int i = 0; i < size; i++) {

            if (distances.get(i) == null) {
                throw new IllegalStateException(
                        "Linha " + i + " da matriz retornada é nula."
                );
            }

            for (int j = 0; j < size; j++) {

                Double value = distances.get(i).get(j);

                if (value == null) {
                    throw new IllegalStateException(
                            "Distância nula na posição [" + i + "][" + j + "]"
                    );
                }
                matrix[i][j] = value;
            }
        }
        return matrix;
    }
}