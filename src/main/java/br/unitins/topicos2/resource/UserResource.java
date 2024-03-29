package br.unitins.topicos2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

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

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @Path("/insert/user/")
    @RolesAllowed({"Admin"})
    public Response insert(UserDTO dto){
        try{
            LOG.info("Inserindo um usuario");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch(ValidationException e){
            LOG.error("Erro ao inserir o usuario");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }

    }

    @PUT
    @Transactional
    @Path("/update/user/{id}")
    @RolesAllowed({"Admin"})
    public Response update(@PathParam("id") Long id, UserDTO dto){

        try{
            LOG.infof("Update em usuario %s", dto.email());
            service.update(id, dto);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG.error("Update não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/delete/user/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id){

        try{
            LOG.info("Deletando usuario");
            service.delete(id);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG.error("Deleção não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @POST
    @Transactional
    @Path("/phone/insert/{id}")
    @RolesAllowed({"Admin"})
    public Response insertPhone(@PathParam("id") Long id, PhoneDTO dto){
        try{
            LOG.infof("Inserindo telefone ao usuario de id %s", id);
            return Response.status(Status.CREATED).entity(service.insertPhone(id, dto)).build();
        } catch(ValidationException e) {
            LOG.error("Inserção de telefone não concluida");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }


    @PUT
    @Transactional
    @Path("/phone/update/{id}")
    @RolesAllowed({"Admin"})
    public Response updatePhone(@PathParam("id") Long id, PhoneDTO dto){

        try{
            LOG.info("Update em telefone");
            service.updatePhone(id, dto);
            return Response.noContent().build();
        } catch(NotFoundException e) {
            LOG.error("Update em telefone não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        } catch(ValidationException e){
            LOG.error("Erro de validação");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/phone/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response deletePhone(@PathParam("id") Long id){
        try{
            LOG.infof("Deletando telefone de id %s", id);
            service.deletePhone(id);
            return Response.noContent().build();
        } catch(NotFoundException e) {
            LOG.error("Deleção de telefone não concluida");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
        
    }

    @GET
    @Path("/phone")
    @RolesAllowed({"Admin"})
    public Response findAllPhones(){
        try{
            LOG.info("Buscando todos os telefones");
            return Response.ok(service.findAllPhones()).build();
        } catch(NotFoundException e) {
            LOG.error("Telefones não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/phone/{id}")
    @RolesAllowed({"Admin"})
    public Response findPhoneByUserId(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando telefone de id %s", id);
            return Response.ok(service.findPhoneByUserId(id)).build();   
        } catch(NotFoundException e) {
            LOG.error("Telefone não encontrado");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @RolesAllowed({"Admin"})
    public Response findAll(){
        try{
            LOG.info("Buscando todos os usuarios");
            return Response.ok(service.findAll()).build();
        } catch(NotFoundException e) {
            LOG.error("Usuarios não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando usuario de id %s", id);
            return Response.ok(service.findById(id)).build();
        } catch(NotFoundException e) {
            LOG.error("Usuario não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @RolesAllowed({"Admin"})
    @Path("/search/name/{username}")
    public Response findByUsername(@PathParam("username") String username){
        try{
            LOG.infof("Buscando %s", username);
            return Response.ok(service.findByUsername(username)).build();
        } catch(NotFoundException e) {
            LOG.error("Usuario não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }
    
}
