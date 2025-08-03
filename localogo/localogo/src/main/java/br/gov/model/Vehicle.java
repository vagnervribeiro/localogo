package br.gov.model;

public class Vehicle {
    private Long id;
    private String model;
    private String engine;
    private int year;
    private VehicleStatus status;

    public Vehicle(Long id, String model, String engine, int year, VehicleStatus status) {
        this.id = id;
        this.model = model;
        this.engine = engine;
        this.year = year;
        this.status = status;
    }

    // Construtor alternativo sem id (caso o id seja gerado depois)
    public Vehicle(String model, String engine, int year, VehicleStatus status) {
        this.model = model;
        this.engine = engine;
        this.year = year;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getEngine() { return engine; }
    public void setEngine(String engine) { this.engine = engine; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public VehicleStatus getStatus() { return status; }
    public void setStatus(VehicleStatus status) { this.status = status; }

    // Exemplo de método simples para saber se está alugado
    public boolean isRented() {
        return VehicleStatus.RENTED.equals(this.status);
    }
}

/*A classe `Vehicle` representa um veículo no sistema, armazenando informações como id, modelo, motor,
ano de fabricação e status (ex: disponível ou alugado). Ela serve como entidade principal para
operações de cadastro,consulta e atualização de veículos.*/