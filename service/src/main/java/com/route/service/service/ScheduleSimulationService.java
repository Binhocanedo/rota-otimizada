package com.route.service.service;

import com.route.service.dto.RouteStopDTO;
import com.route.service.model.CustomerModel;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleSimulationService {

    private static final LocalTime START_OF_DAY = LocalTime.of(8, 0);

    public List<RouteStopDTO> simulate(
            List<CustomerModel> customers,
            List<Integer> route,
            double[][] durationMatrix,
            LocalTime lunchStart,
            LocalTime lunchEnd) {

        List<RouteStopDTO> result = new ArrayList<>();

        LocalTime currentTime = START_OF_DAY;

        for (int i = 1; i < route.size() - 1; i++) {

            int currentIndex = route.get(i);

            CustomerModel customer = customers.get(currentIndex - 1);

            int visitDuration = customer.getVisitDurationMinutes();

            // Chegada
            LocalTime arrival = currentTime;

            // Respeita almoço
            if (!arrival.isBefore(lunchStart) && arrival.isBefore(lunchEnd)) {
                arrival = lunchEnd;
            }

            LocalTime departure = arrival.plusMinutes(visitDuration);

            RouteStopDTO stop = new RouteStopDTO();
            stop.setCustomerName(customer.getName());
            stop.setArrivalAt(arrival);
            stop.setDepartureAt(departure);
            stop.setVisitDurationMinutes(visitDuration);

            result.add(stop);

            currentTime = departure;

            // calcula deslocamento até próximo cliente
            if (i < route.size() - 2) {

                int nextIndex = route.get(i + 1);

                double travelSeconds = durationMatrix[currentIndex][nextIndex];

                long travelMinutes = (long) Math.ceil(travelSeconds / 60);

                currentTime = currentTime.plusMinutes(travelMinutes);
            }
        }

        return result;
    }
}