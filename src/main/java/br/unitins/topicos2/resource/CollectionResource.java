package br.unitins.topicos2.resource;

import br.unitins.topicos2.dto.CollectionDTO;
import br.unitins.topicos2.dto.CollectionResponseDTO;
import br.unitins.topicos2.form.CollectionImageForm;
import br.unitins.topicos2.service.CollectionFileService;
import br.unitins.topicos2.service.CollectionService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos2.application.Error;

@Path("/collections")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionResource {

    @Inject
    CollectionService service;

    @Inject
    CollectionFileService fileService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(CollectionDTO dto){
        try{
            LOG.info("Inserindo coleção");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch(Exception e){
            LOG.error("Erro ao inserir coleção");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response update(@PathParam("id") Long id, CollectionDTO dto){
        try{
            LOG.info("Atualizando os dados da coleção");
            service.update(id, dto);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG.error("Erro ao atualizar dados da coleção");
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id){
        try{
            LOG.infof("Deletando coleção de id %s", id);
            service.delete(id);
            return Response.noContent().build();
        } catch(NotFoundException e){
            LOG.error("Erro ao deletar coleção");
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @PATCH
    @Path("/image/upload")
    @RolesAllowed({"Admin"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertImage(@MultipartForm CollectionImageForm form){
        //String imageName;
        Long id = form.getId();
        try{
            LOG.infof("Inserindo imagem ao produto de id %s", id);
            String imageName = fileService.save(form.getImageName(), form.getImage());
            service.insertImage(id, imageName);
            return Response.noContent().build();
        } catch(IOException e){
            LOG.error("Erro ao inserir imagem");
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }
    }

    @GET
    @Path("/image/download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("id")Long id){

        LOG.infof("Buscando imagem do usuario de id %s", id);
        CollectionResponseDTO collection = service.findById(id);
        String imageName = collection.imageName();

        ResponseBuilder response = Response.ok(fileService.getFile(imageName));
        response.header("Content-Disposition", "attachment;filename="+imageName);
        return response.build();
    }

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){
        try{
            LOG.info("Buscando coleções");
            return Response.ok(service.getAll(page, pageSize)).build();
        } catch(NotFoundException e){
            LOG.error("Erro ao buscar por coleção");
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando por coleção de id %s", id);
            return Response.ok(service.findById(id)).build();
        } catch(NotFoundException e){
            LOG.error("Erro ao buscar por coleção");
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/{CollectionName}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByCollectionName(@PathParam("CollectionName") String collectionName){
        try{
            LOG.infof("Buscando por %s", collectionName);
            return Response.ok(service.findByName(collectionName)).build();
        } catch(NotFoundException e){
            LOG.error("Erro ao buscar por coleção");
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
