package com.route.service.service;


import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RouteOptimizationService {

    /**
     * Gera rota inicial usando Nearest Neighbor
     */
    public List<Integer> nearestNeighbor(double[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Matriz de distância inválida.");
        }

        int n = matrix.length;
        boolean[] visited = new boolean[n];
        List<Integer> route = new ArrayList<>();

        int current = 0; // começa no gerente (posição 0)
        route.add(current);
        visited[current] = true;

        for (int count = 1; count < n; count++) {

            double minDistance = Double.MAX_VALUE;
            int next = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && matrix[current][i] < minDistance) {
                    minDistance = matrix[current][i];
                    next = i;
                }
            }

            if (next == -1) {
                throw new IllegalStateException("Não foi possível encontrar próximo nó válido.");
            }

            route.add(next);
            visited[next] = true;
            current = next;
        }

        route.add(0); // volta ao ponto inicial

        return route;
    }

    /**
     * Melhora rota usando algoritmo 2-opt
     */
    public List<Integer> twoOpt(List<Integer> route, double[][] matrix) {

        if (route == null || route.size() < 4) {
            return route;
        }

        boolean improvement = true;
        int size = route.size();

        while (improvement) {
            improvement = false;

            for (int i = 1; i < size - 2; i++) {
                for (int j = i + 1; j < size - 1; j++) {

                    List<Integer> newRoute = new ArrayList<>(route);
                    Collections.reverse(newRoute.subList(i, j + 1));

                    double currentDistance = calculateTotalDistance(route, matrix);
                    double newDistance = calculateTotalDistance(newRoute, matrix);

                    if (newDistance < currentDistance) {
                        route = newRoute;
                        improvement = true;
                        break; // já achou melhoria, sai do loop interno
                    }
                }
                if (improvement) {
                    break; // sai do loop externo também
                }
            }
        }

        return route;
    }

    /**
     * Calcula distância total da rota
     */
    public double calculateTotalDistance(List<Integer> route, double[][] matrix) {

        if (route == null || route.size() < 2) {
            return 0;
        }

        double total = 0;

        for (int i = 0; i < route.size() - 1; i++) {
            total += matrix[route.get(i)][route.get(i + 1)];
        }

        return total;
    }
}