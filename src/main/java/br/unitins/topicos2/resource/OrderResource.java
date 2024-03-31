package br.unitins.topicos2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos2.dto.OrderDTO;
import br.unitins.topicos2.dto.OrderResponseDTO;
import br.unitins.topicos2.service.OrderService;
import br.unitins.topicos2.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService service;

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;


    @POST
    @RolesAllowed({"User"})
    public Response insert(OrderDTO dto) {

        String login = jwt.getSubject();
        
        OrderResponseDTO retorno = service.insert(dto, login);
        return Response.status(201).entity(retorno).build();
    }

    @GET
    @RolesAllowed({"User", "Admin"})
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        
        return Response.ok(service.getAll(page, pageSize)).build();
    }
}
