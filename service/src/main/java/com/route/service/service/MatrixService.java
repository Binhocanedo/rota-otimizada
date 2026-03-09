package com.route.service.service;

import com.route.service.model.CustomerModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrixService {

    public double[][] buildDurationMatrix(List<CustomerModel> customers) {

        int n = customers.size() + 1;

        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i == j) {
                    matrix[i][j] = 0;
                    continue;
                }

                CustomerModel a = customers.get(Math.max(i - 1, 0));
                CustomerModel b = customers.get(Math.max(j - 1, 0));

                double distance = distance(
                        a.getLat(),
                        a.getLng(),
                        b.getLat(),
                        b.getLng()
                );

                double speedKmH = 40;

                double timeHours = distance / speedKmH;

                double timeSeconds = timeHours * 3600;

                matrix[i][j] = timeSeconds;
            }
        }

        return matrix;
    }

    private double distance(
            double lat1,
            double lon1,
            double lat2,
            double lon2) {

        double dx = lat1 - lat2;
        double dy = lon1 - lon2;

        return Math.sqrt(dx * dx + dy * dy) * 111;
    }
}
