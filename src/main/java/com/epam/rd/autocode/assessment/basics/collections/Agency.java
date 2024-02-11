package com.epam.rd.autocode.assessment.basics.collections;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.epam.rd.autocode.assessment.basics.entity.BodyType;
import com.epam.rd.autocode.assessment.basics.entity.Client;
import com.epam.rd.autocode.assessment.basics.entity.Order;
import com.epam.rd.autocode.assessment.basics.entity.Vehicle;

public class Agency implements Find, Sort {

    private List<Vehicle> vehicles;

    private List<Order> orders;

    public Agency() {
        this.vehicles = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public List<Vehicle> sortByID() {
        var sortedList = new ArrayList<>(vehicles);
        var comparator = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        };
        sortedList.sort(comparator);
        return sortedList;
    }

    @Override
    public List<Vehicle> sortByYearOfProduction() {
        var sortedList = new ArrayList<>(vehicles);
        var comparator = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return Integer.compare(o1.getYearOfProduction(), o2.getYearOfProduction());
            }
        };
        sortedList.sort(comparator);
        return sortedList;
    }

    @Override
    public List<Vehicle> sortByOdometer() {
        var sortedList = new ArrayList<>(vehicles);
        var comparator = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return Long.compare(o1.getOdometer(), o2.getOdometer());
            }
        };
        sortedList.sort(comparator);
        return sortedList;
    }

    @Override
    public Set<String> findMakers() {
        Set<String> uniqueMakers = new HashSet<>();
        for (Vehicle vehicle : vehicles) {
            uniqueMakers.add(vehicle.getMake());
        }
        return uniqueMakers;
    }

    @Override
    public Set<BodyType> findBodytypes() {
        Set<BodyType> bodyTypes = new LinkedHashSet<>();
        for (Vehicle vehicle : vehicles) {
            bodyTypes.add(vehicle.getBodyType());
        }
        return bodyTypes;
    }

    @Override
    public Map<String, List<Vehicle>> findVehicleGrouppedByMake() {
        Map<String, List<Vehicle>> vehiclesByMake = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            vehiclesByMake.putIfAbsent(vehicle.getMake(), new ArrayList<>());
            vehiclesByMake.get(vehicle.getMake()).add(vehicle);
        }
        return vehiclesByMake;
    }

    @Override
    public List<Client> findTopClientsByPrices(List<Client> clients, int maxCount) {
        Map<Long, Client> clientsMap = new HashMap<>();
        for (Client client : clients) {
            clientsMap.put(client.getId(), client);
        }

        Map<Long, BigDecimal> results = new TreeMap<>();
        for (Order order : orders) {
            if (!clientsMap.containsKey(order.getClientId())) {
                continue;
            }
            results.putIfAbsent(order.getClientId(), new BigDecimal(0));
            var newPrice = results.get(order.getClientId()).add(order.getPrice());
            results.put(order.getClientId(), newPrice);
        }

        var comparator = new Comparator<Map.Entry<Long, BigDecimal>>() {
            @Override
            public int compare(Entry<Long, BigDecimal> o1, Entry<Long, BigDecimal> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };
        var sortedEntries = new ArrayList<>(results.entrySet());
        sortedEntries.sort(comparator);

        List<Client> topClients = new ArrayList<>();
        for (var entry : sortedEntries) {
            topClients.add(clientsMap.get(entry.getKey()));
        }

        return topClients.size() >= maxCount ? topClients.subList(0, maxCount) : topClients;
    }

    @Override
    public List<Client> findClientsWithAveragePriceNoLessThan(List<Client> clients, int average) {
        Map<Long, Client> clientsMap = new HashMap<>();
        for (Client client : clients) {
            clientsMap.put(client.getId(), client);
        }

        Map<Long, List<BigDecimal>> results = new HashMap<>();
        for (Order order : orders) {
            if (!clientsMap.containsKey(order.getClientId())) {
                continue;
            }
            results.putIfAbsent(order.getClientId(), new ArrayList<>());
            var prefixPrices = results.get(order.getClientId());
            var newPrice = prefixPrices.isEmpty() ? order.getPrice()
                    : prefixPrices.get(prefixPrices.size() - 1).add(order.getPrice());
            prefixPrices.add(newPrice);
        }

        List<Client> clientsWithAveragePrice = new ArrayList<>();
        for (var entry : results.entrySet()) {
            var prefixPrices = entry.getValue();
            int amountOfOrders = prefixPrices.size();

            var averagePrice = prefixPrices
                    .get(amountOfOrders - 1)
                    .divide(BigDecimal.valueOf(amountOfOrders), RoundingMode.HALF_DOWN);

            if (averagePrice.compareTo(BigDecimal.valueOf(average)) >= 0) {
                clientsWithAveragePrice.add(clientsMap.get(entry.getKey()));
            }
        }

        return clientsWithAveragePrice;
    }

    @Override
    public List<Vehicle> findMostOrderedVehicles(int maxCount) {
        Map<Long, Vehicle> vehiclesMap = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            vehiclesMap.put(vehicle.getId(), vehicle);
        }

        Map<Long, Integer> results = new HashMap<>();
        for (Order order : orders) {
            if (!vehiclesMap.containsKey(order.getVehicleId())) {
                continue;
            }
            results.putIfAbsent(order.getVehicleId(), 0);
            results.put(order.getVehicleId(), results.get(order.getVehicleId()) + 1);
        }

        var comparator = new Comparator<Map.Entry<Long, Integer>>() {
            @Override
            public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        };
        var sortedEntries = new ArrayList<>(results.entrySet());
        sortedEntries.sort(comparator);

        List<Vehicle> mostOrderedVehicles = new ArrayList<>();
        for (var entry : sortedEntries) {
            mostOrderedVehicles.add(vehiclesMap.get(entry.getKey()));
        }

        return mostOrderedVehicles.subList(0, maxCount);
    }

}