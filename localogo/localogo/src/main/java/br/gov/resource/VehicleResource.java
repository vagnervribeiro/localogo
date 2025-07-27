package br.gov.resource;

import br.gov.dto.UpdateVehicleStatusRequestBody;
import br.gov.dto.VehicleRequestBody;
import br.gov.dto.VehicleResponseBody;
import br.gov.model.Vehicle;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Path("/api/v1/vehicle")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleResource {


    private final static Map<Long, Vehicle> VEHICLES = new ConcurrentHashMap<>();


    @POST

    public Response create(
            VehicleRequestBody body

    ) {

        Vehicle vehicle = new Vehicle(
                body.getModel(),
                body.getEngine(),
                body.getYear()

        );


        return Response.created(URI.create("/api/v1/vehicle" + vehicle.getId())).build();

    }

    @GET
    public List<VehicleResponseBody> findAll(){
        return VEHICLES.values()

                .stream()
                .map(vehicle -> {
                    return new VehicleResponseBody(vehicle);
                })
                .toList();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id){

        Vehicle vehicle = VEHICLES.get(id);

        if (vehicle == null) {
            return Response.status(404).build();
        }

        VehicleResponseBody vehicleResponseBody = new VehicleResponseBody(vehicle);

        return Response.ok(vehicle).build();

    }

    @DELETE
    @Path( "{id}")
    public Response delete(@PathParam("id") Long id) {
        Vehicle vehicle = VEHICLES.get(id);
        if (vehicle == null) {
            return Response.status(404).build();
        }

        if (vehicle.isRented()) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        VEHICLES.remove(id);
        return Response.noContent().build();



    }

    @PATCH
    @Path("{id}")
    public Response changeVehicle(@PathParam("id") Long id, UpdateVehicleStatusRequestBody body) {

        Vehicle vehicle = VEHICLES.get(id);
        if (vehicle == null) {
            return Response.status(404).build();
        }

        try {

            vehicle.setStatus(body.status());
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        VEHICLES.put(id, vehicle);

        return Response.noContent().build();



    }
}





/*A classe VehicleResource é responsável por mostrar endpoints manipulação de veículos na API.Utiliza as classes Vehicle, VehicleRequestBody e VehicleResponseBody para manipular e transferir dados entre a API e o banco de dados.
Esses dados e métodos permitem cadastrar e consultar veículos via requisições HTTP*/
