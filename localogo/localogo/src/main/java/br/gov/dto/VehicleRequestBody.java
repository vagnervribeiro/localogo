package br.gov.dto;

import br.gov.model.VehicleStatus;

public class VehicleRequestBody {

    private String model;
    private VehicleStatus status;
    private int year;
    private String engine;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}


/*Representa os dados que são enviados para a API ao criar ou atualizar um veículo.
Cada campo corresponde a uma característica do veículo.
Os dados são usados para transferir informações entre o front-end ou outro sistema e o backend da aplicação.*/
