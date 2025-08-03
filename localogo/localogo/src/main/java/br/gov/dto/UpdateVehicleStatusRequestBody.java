package br.gov.dto;

import br.gov.model.VehicleStatus;

public record UpdateVehicleStatusRequestBody(
        VehicleStatus status
) {
}
/* Representa corpo da requisição. Serve para atualizar o status de um veículo na API.
Indica a inclusão de um ao veículo, como disponível ou alugado. Recebe dados de atualização via requisições HTTP.*/