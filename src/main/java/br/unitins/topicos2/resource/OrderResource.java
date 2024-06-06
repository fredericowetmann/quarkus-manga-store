package br.unitins.topicos2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos2.dto.OrderDTO;
import br.unitins.topicos2.dto.OrderResponseDTO;
import br.unitins.topicos2.service.OrderService;
import br.unitins.topicos2.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
    @RolesAllowed({"User", "Admin"})
    @Path("/insert")
    public Response insert(OrderDTO dto) {

        String login = jwt.getSubject();
        
        OrderResponseDTO retorno = service.insert(dto, login);
        return Response.status(201).entity(retorno).build();
    }

    @GET
    @Path("/user/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response findByUser(@PathParam("id")Long id) {
        return Response.ok(service.findByUserId(id)).build();
    }

    @GET
    @Path("/orders/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response findByOrdersId(@PathParam("id")Long id) {
        return Response.ok(service.findByOrderId(id)).build();
    }
    
    @GET
    @RolesAllowed({"User", "Admin"})
    @Path("/all")
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }
}
