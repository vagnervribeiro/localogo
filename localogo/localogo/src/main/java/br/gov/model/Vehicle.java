package br.gov.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Vehicle {

    private static final Map<VehicleStatus, Set<VehicleStatus>> VEHICLE_STATUS = new HashMap<>() {
    };


    static {
            VEHICLE_STATUS.put(VehicleStatus.AVAILABLE, Set.of(VehicleStatus.RENTED, VehicleStatus.UNDER_MAINTENANCE));
            VEHICLE_STATUS.put(VehicleStatus.RENTED, Set.of(VehicleStatus.AVAILABLE, VehicleStatus.UNDER_MAINTENANCE));
            VEHICLE_STATUS.put(VehicleStatus.UNDER_MAINTENANCE, Set.of(VehicleStatus.AVAILABLE));

    }

    private static final AtomicLong ATOMIC_LONG = new AtomicLong();
    private Long id;
    private String model;
    private VehicleStatus status;
    private int year;
    private String engine;

// Construtor padrão

    public Vehicle(String model, String engine, int year, VehicleStatus status){
            this.id = ATOMIC_LONG.getAndIncrement();
            this.status = VehicleStatus.AVAILABLE;
            this.model = model;
            this.engine = engine;
            this.year = year;
        }

    public Vehicle(String model, String engine, int year) {
    }

    public Long getId() { return id; }

    public String getModel() { return model; }

    public VehicleStatus getStatus() { return status; }

    public int getYear() { return year; }

    public String getEngine() { return engine; }

    public boolean isRented(){
        return this.getStatus().equals(VehicleStatus.RENTED);
    }

    public void setStatus(VehicleStatus incomingStatus) {
        Set<VehicleStatus> possibleStatus = VEHICLE_STATUS.get(this.status);
        if (incomingStatus.equals(this.status)) {
            return;
        }
        if (possibleStatus.contains(incomingStatus)) {
            this.status = incomingStatus;
        } else {
            throw new RuntimeException("Validation error, possible status are: " + possibleStatus);
        }

    }
}



/* A classe Vehicle representa um veículo no sistema. Esses campos permitem armazenar e manipular informações essenciais sobre cada veículo na aplicação.*/