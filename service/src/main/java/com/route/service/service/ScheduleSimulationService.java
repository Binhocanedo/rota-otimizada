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
    private static final int TRAVEL_MINUTES = 30;

    public List<RouteStopDTO> simulate(
            List<CustomerModel> orderedCustomers,
            LocalTime lunchStart,
            LocalTime lunchEnd) {

        List<RouteStopDTO> result = new ArrayList<>();

        LocalTime currentTime = START_OF_DAY;
        boolean firstCustomer = true;

        for (CustomerModel customer : orderedCustomers) {

            int duration = customer.getVisitDurationMinutes();

            // Se não for o primeiro cliente, adiciona tempo de deslocamento
            if (!firstCustomer) {
                currentTime = currentTime.plusMinutes(TRAVEL_MINUTES);
            }

            firstCustomer = false;

            // Se estiver no horário de almoço, pula
            if (!currentTime.isBefore(lunchStart) && currentTime.isBefore(lunchEnd)) {
                currentTime = lunchEnd;
            }

            LocalTime potentialEnd = currentTime.plusMinutes(duration);

            // Se ultrapassar almoço, empurra para depois
            if (currentTime.isBefore(lunchStart) && potentialEnd.isAfter(lunchStart)) {
                currentTime = lunchEnd;
                potentialEnd = currentTime.plusMinutes(duration);
            }

            RouteStopDTO stop = new RouteStopDTO();
            stop.setCustomerName(customer.getName());
            stop.setArrivalAt(currentTime);
            stop.setDepartureAt(potentialEnd);
            stop.setVisitDurationMinutes(duration);

            result.add(stop);

            currentTime = potentialEnd;
        }

        return result;
    }
}
