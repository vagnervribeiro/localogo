package br.gov.service;

import br.gov.model.Vehicle;
import br.gov.model.VehicleStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleService {

    private static final Map<VehicleStatus, Set<VehicleStatus>> POSSIBLE_VEHICLE_STATUS_TRANSITIONS = new HashMap<>();

    static {
        POSSIBLE_VEHICLE_STATUS_TRANSITIONS.put(VehicleStatus.AVAILABLE, Set.of(VehicleStatus.RENTED, VehicleStatus.UNDER_MAINTENANCE));
        POSSIBLE_VEHICLE_STATUS_TRANSITIONS.put(VehicleStatus.RENTED, Set.of(VehicleStatus.AVAILABLE, VehicleStatus.UNDER_MAINTENANCE));
        POSSIBLE_VEHICLE_STATUS_TRANSITIONS.put(VehicleStatus.UNDER_MAINTENANCE, Set.of(VehicleStatus.AVAILABLE));
    }

    private final Map<Long, Vehicle> vehicles = new ConcurrentHashMap<>();

    public Vehicle createVehicle(String model, String engine, int year) {
        Vehicle vehicle = new Vehicle(model, engine, year, VehicleStatus.AVAILABLE);
        vehicles.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    public Vehicle findById(Long id) {
        return vehicles.get(id);
    }

    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicles.values());
    }

    public boolean deleteVehicle(Long id) {
        Vehicle vehicle = vehicles.get(id);
        if (vehicle == null || vehicle.isRented()) {
            return false;
        }
        vehicles.remove(id);
        return true;
    }

    public void changeStatus(Long id, VehicleStatus newStatus) {
        Vehicle vehicle = vehicles.get(id);
        if (vehicle == null) {
            throw new RuntimeException("Veículo não encontrado");
        }
        Set<VehicleStatus> possibleStatus = POSSIBLE_VEHICLE_STATUS_TRANSITIONS.get(vehicle.getStatus());
        if (newStatus.equals(vehicle.getStatus())) {
            return;
        }
        if (possibleStatus != null && possibleStatus.contains(newStatus)) {
            vehicle.setStatus(newStatus);
        } else {
            throw new RuntimeException("Status inválido. Possíveis: " + possibleStatus);
        }
    }
}