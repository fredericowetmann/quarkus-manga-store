package br.unitins.topicos2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos2.dto.LoginDTO;
import br.unitins.topicos2.dto.PhoneDTO;
import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import br.unitins.topicos2.validation.ValidationException;

import br.unitins.topicos2.application.Error;

@Path("/basicUsers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicRegistrationResource {

    @Inject
    UserService userService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Path("/insert/")
    public Response insertBasicUser(LoginDTO dto){
        try{
            LOG.info("Inserindo um usuario basico");
            return Response.status(Status.CREATED).entity(userService.insertBasicUser(dto)).build();
        } catch(ValidationException e){
            LOG.error("Erro ao inserir o usuario basico");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }
    
}
