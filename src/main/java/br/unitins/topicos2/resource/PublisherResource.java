package br.unitins.topicos2.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos2.dto.PublisherDTO;
import br.unitins.topicos2.service.PublisherService;
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

import br.unitins.topicos2.application.Error;

@Path("/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {

    @Inject
    PublisherService service;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    public Response insert(PublisherDTO dto){
        try{
            LOG.info("Inserindo publisher");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch(Exception e){
            LOG.error("Erro ao inserir a publisher");
            e.printStackTrace();
            Error error = new Error("400", "Não foi possivel concluir a ação");
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PublisherDTO dto){
        try{
            LOG.info("Iniciando update da publisher");
            service.update(id, dto);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG.error("Erro ao encontrar a publisher");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        try{
            LOG.infof("Deletando publisher de id %s", id);
            service.delete(id);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG.error("Erro ao deletar a publisher");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){
        try{
            LOG.info("Buscando publishers");
            return Response.ok(service.getAll(page, pageSize)).build();
        } catch(NotFoundException e){
            LOG.error("Publishers não encontradas");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando por publisher de id %s", id);
            return Response.ok(service.findById(id)).build();
        } catch(NotFoundException e){
            LOG.error("Publisher não encontrada");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/name/{name}")
    public Response findByName(@PathParam("name") String name){
        try{
            LOG.infof("Buscando por %s", name);
            return Response.ok(service.findByName(name)).build();
        } catch(NotFoundException e){
            LOG.error("Publisher não encontrada");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/count")
    public long count(){
        return service.count();
    }
    
}
