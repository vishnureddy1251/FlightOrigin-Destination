package com.example.flightapp.service;

import com.example.flightapp.model.Flight;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.*;

@Service
public class FlightService {

    private List<Flight> flights;

    public FlightService(){
        initializeFlights();
    }

    private void initializeFlights(){
        flights = new ArrayList<>();
        flights.add(new Flight("DFW", "ORD", 101, 45, LocalTime.of(8,0)));
        flights.add(new Flight("DFW", "ORD", 102, 55, LocalTime.of(9, 0)));
        flights.add(new Flight("DFW", "ORD", 103, 65, LocalTime.of(18,0)));
        flights.add(new Flight("DFW", "LIT", 201, 34, LocalTime.of(14,0)));
        flights.add(new Flight("LIT", "JFK", 301, 65, LocalTime.of(17,0)));

    }

    // Lowest Price
    public Map<String, Object> getLowestPriceToChicago(){
        Flight cheapestFlight = flights.stream()
                .filter(f -> f.getDestination().equals("ORD"))
                .min(Comparator.comparing(Flight::getPrice))
                .orElse(null);

        Map<String, Object> result = new HashMap<>();
        if (cheapestFlight != null){
            result.put("flightNumber", cheapestFlight.getFlightNumber());
            result.put("price", cheapestFlight.getPrice());
            result.put("departureTime", cheapestFlight.getDepartureTime().toString());
        }
        return result;
    }

    // Afternoon flight
    public Map<String, Object> getAfternoonFlightPrice(){
        LocalTime afternoonStart = LocalTime.of(12,0);
        LocalTime afternoonEnd = LocalTime.of(18,0);

        Flight afternoonFlight = flights.stream()
                .filter(f ->{
                        LocalTime time = f.getDepartureTime();
                        return !time.isBefore(afternoonStart) && !time.isAfter(afternoonEnd);
                }).findFirst()
                .orElse(null);

        Map<String, Object> result = new HashMap<>();
        if (afternoonFlight != null){
            result.put("flightNumber", afternoonFlight.getFlightNumber());
            result.put("price", afternoonFlight.getPrice());
            result.put("departureTime", afternoonFlight.getDepartureTime().toString());
        }
        return result;
    }

    // Price to JFK

    public Map<String, Object> getPriceToJFK(){
        Map<String, Object> result = new HashMap<>();

        // check for direct flight
        Optional<Flight> directFlight = flights.stream()
                .filter(f -> f.getOrigin().equals("DFW") && f.getDestination().equals("JFK"))
                .findFirst();
        if(directFlight.isPresent()){
            result.put("isDirect", true);
            result.put("totalPrice", directFlight.get().getPrice());
            result.put("flights", Arrays.asList(directFlight.get()));
        }else {
            // Find Connecting flights
            List<Flight> connectingFlights = findConnectingFlights("DFW", "JFK");

            if (!connectingFlights.isEmpty()) {
                double totalprice = connectingFlights.stream()
                        .mapToDouble(Flight::getPrice)
                        .sum();

                result.put("isDirect", false);
                result.put("totalPrice", totalprice);
                result.put("flights", connectingFlights);
                result.put("note", "connecting flight via" + connectingFlights.get(0).getDestination());
            }else {
                result.put("message", "No Route avaiable to JFK");
            }
        }
        return result;
    }

    private List<Flight> findConnectingFlights(String origin, String destination) {
        List<Flight> connecting = new ArrayList<>();

        for (Flight firstleg : flights){
            if (firstleg.getOrigin().equals(origin)){
                for (Flight secondLeg : flights){
                    if (secondLeg.getOrigin().equals(firstleg.getDestination()) &&
                        secondLeg.getDestination().equals(destination) &&
                                secondLeg.getDepartureTime().isAfter(firstleg.getDepartureTime())){
                        connecting.add(firstleg);
                        connecting.add(secondLeg);
                        return connecting;

                    }
                }
            }
        }
        return connecting;
    }

    public List<Flight> getFlights() {
        return new ArrayList<>(flights);
    }

}
