package br.unitins.topicos2.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos2.dto.AddressDTO;
import br.unitins.topicos2.repository.AddressRepository;
import br.unitins.topicos2.service.AddressService;
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

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    AddressService service;

    @Inject
    AddressRepository repository;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    public Response insert(AddressDTO dto){

        try{
            LOG.info("Inserindo endereço");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch(ValidationException e){
            LOG.error("endereço não inserido");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{idAddress}")
    public Response update(@PathParam("idAddress") Long idAddress, AddressDTO dto){

        try{
            LOG.infof("Update em endereço de id %s", idAddress);
            service.update(idAddress, dto);
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
    @Path("/{id}")
    public Response delete(@PathParam("id") Long idAddress){
        try{
            LOG.infof("Deletando endereço de id %s", idAddress);
            service.delete(idAddress);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG .error("Endereço não encontrado");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando por endereço de id %s", id);
            return Response.ok(service.findById(id)).build();
        } catch(NotFoundException e){
            LOG.error("Erro ao buscar por endereço");
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    public Response findAll(){

        try{
            LOG.info("Buscando todos os endereços");
            return Response.ok(service.findAll()).build();
        } catch(NotFoundException e){
            LOG .error("Endereços não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @GET
    @Path("/search/city/{cityId}")
    public Response findByCity(@PathParam("cityId")Long cityId){

        try{
            LOG.infof("Buscando endereços da cidade de id %s", cityId);
            return Response.ok(service.findByCity(cityId)).build();
        } catch(NotFoundException e){
            LOG .error("Endereços não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }
    
    @GET
    @Path("/count")
    public long count(){
        return service.count();
    }
    
}
