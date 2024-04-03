package br.unitins.topicos2.resource;

import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.service.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;



@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;

    @POST
    public Response insert(UserDTO dto) {
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(UserDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){
        return Response.ok(service.getAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/name/{name}")
    public Response findByName(@PathParam("name") String name) {
        return Response.ok(service.findByName(name)).build();
    }
}
