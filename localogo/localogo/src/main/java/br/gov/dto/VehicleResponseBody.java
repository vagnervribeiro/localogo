package br.gov.dto;

import br.gov.model.Vehicle;
import br.gov.model.VehicleStatus;

public record VehicleResponseBody(
        Long id,
        String model,
        VehicleStatus status,
        int year,
        String engine,
        String carTitle
) {
    public VehicleResponseBody(Vehicle vehicle) {
        this(vehicle.getId(),vehicle.getModel(), vehicle.getStatus(), vehicle.getYear(), vehicle.getEngine(),
                vehicle.getModel() + " " + vehicle.getYear() + " " + vehicle.getEngine());
    }
}

/*representa os dados que são retornados pela API ao consultar informações de um veículo.
Os dados são enviados do backend para o cliente.*/