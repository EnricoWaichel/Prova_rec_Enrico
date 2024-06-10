package controller.carros;

import exception.CarrosException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entity.carros.Montadora;
import service.carros.MontadoraService;

@Path("/montadora")
public class MontadoraController {

    private MontadoraService montadoraService = new MontadoraService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvarMontadora(Montadora montadora) {
        try {
            Montadora novaMontadora = montadoraService.salvarMontadora(montadora);
            return Response.status(Response.Status.CREATED).entity(novaMontadora).build();
        } catch (CarrosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarMontadoraPorId(@PathParam("id") int id) {
        Montadora montadora = montadoraService.consultarMontadoraPorId(id);
        if (montadora != null) {
            return Response.ok(montadora).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Montadora não encontrada").build();
        }
    }
}
