package br.gov.resource;

import br.gov.dto.UpdateVehicleStatusRequestBody;
import br.gov.dto.VehicleRequestBody;
import br.gov.dto.VehicleResponseBody;
import br.gov.model.Vehicle;
import br.gov.service.VehicleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/api/v1/vehicle")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleResource {

//    private final static Map<Long, Vehicle> VEHICLES = new ConcurrentHashMap<>();

    @Inject
    VehicleService vehicleService;

    @POST
    public Response create(VehicleRequestBody body) {
        Vehicle vehicle = vehicleService.createVehicle(
                body.getModel(),
                body.getEngine(),
                body.getYear()
        );
        return Response.created(URI.create("/api/v1/vehicle/" + vehicle.getId())).build();
    }

    @GET
    public List<VehicleResponseBody> findAll() {
        return vehicleService.findAll()
                .stream()
                .map(VehicleResponseBody::new)
                .toList();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        Vehicle vehicle = vehicleService.findById(id);
        if (vehicle == null) {
            return Response.status(404).build();
        }
        VehicleResponseBody vehicleResponseBody = new VehicleResponseBody(vehicle);
        return Response.ok(vehicleResponseBody).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Vehicle vehicle = vehicleService.findById(id);
        if (vehicle == null) {
            return Response.status(404).build();
        }

        if (vehicle.isRented()) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        boolean deleted = vehicleService.deleteVehicle(id);
        if (!deleted) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}")
    public Response changeVehicle(@PathParam("id") Long id, UpdateVehicleStatusRequestBody body) {
        Vehicle vehicle = vehicleService.findById(id);
        if (vehicle == null) {
            return Response.status(404).build();
        }

        try {
            vehicleService.changeStatus(id, body.status());
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.noContent().build();
    }
}

/* É responsável por mostrar endpoints de manipulação de veículos na API.
Utiliza as classes VehicleService, VehicleRequestBody e VehicleResponseBody para manipular
e transferir dados entre a API e o banco de dados. Permitem cadastrar e consultar veículos via requisições HTTP*/
