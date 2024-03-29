package br.unitins.topicos2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos2.dto.AddressDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import br.unitins.topicos2.repository.AddressRepository;
import br.unitins.topicos2.service.AddressService;
import br.unitins.topicos2.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotAuthorizedException;
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

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    AddressService service;

    @Inject
    AddressRepository repository;

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Path("/insert/")
    @RolesAllowed({"Admin", "User"})
    public Response insert(AddressDTO dto){

        String login = jwt.getSubject();

        UserResponseDTO user = userService.findByEmail(login);

        Long id = user.id();

        try{
            LOG.info("Inserindo endereço");
            return Response.status(Status.CREATED).entity(service.insert(id, dto)).build();
        } catch(ValidationException e){
            LOG.error("endereço não inserido");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/update/{idAddress}")
    @RolesAllowed({"Admin", "User"})
    public Response update(@PathParam("idAddress") Long idAddress, AddressDTO dto){

        String login = jwt.getSubject();

        UserResponseDTO user = userService.findByEmail(login);

        Long id = user.id();


        try{
            LOG.infof("Update em endereço de id %s", id);
            service.update(idAddress, id, dto);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG .error("Endereço não encontrado");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        } catch(ValidationException e){
            LOG .error("Update não concluido");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    @RolesAllowed({"Admin", "User"})
    public Response delete(@PathParam("id") Long idAddress){

        String login = jwt.getSubject();

        UserResponseDTO user = userService.findByEmail(login);

        Long id = user.id();


        try{
            LOG.infof("Deletando endereço de id %s", id);
            service.delete(idAddress,id);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG .error("Endereço não encontrado");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @GET
    @RolesAllowed({"Admin", "User"})
    public Response findAll(){

        String login = jwt.getSubject();

        UserResponseDTO user = userService.findByEmail(login);

        Long id = user.id();

        try{
            LOG.info("Buscando todos os endereços");
            return Response.ok(service.findAll(id)).build();
        } catch(NotFoundException e){
            LOG .error("Endereços não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @GET
    @Path("/search/city/{cityId}")
    @RolesAllowed({"Admin", "User"})
    public Response findByCity(@PathParam("cityId")Long cityId){

        String login = jwt.getSubject();

        UserResponseDTO user = userService.findByEmail(login);

        Long id = user.id();

        try{
            LOG.infof("Buscando endereços da cidade de id %s", cityId);
            return Response.ok(service.findByUserAndCity(id, cityId)).build();
        } catch(NotFoundException e){
            LOG .error("Endereços não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        } catch(NotAuthorizedException e){
            LOG .error("Não autorizado");
            e.printStackTrace();
            Error error = new Error("401", e.getMessage());
            return Response.status(Status.UNAUTHORIZED).entity(error).build();
        }
    }
    
}
