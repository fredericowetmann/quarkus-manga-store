package br.unitins.topicos2.resource;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos2.dto.MangaDTO;
import br.unitins.topicos2.dto.MangaResponseDTO;
import br.unitins.topicos2.form.MangaImageForm;
import br.unitins.topicos2.repository.MangaRepository;
import br.unitins.topicos2.service.MangaFileService;
import br.unitins.topicos2.service.MangaService;
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

import br.unitins.topicos2.application.Error;

@Path("/mangas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MangaResource {

    @Inject
    MangaService service;

    @Inject
    MangaRepository repository;

    @Inject
    MangaFileService fileService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(MangaDTO dto){
        try{
            LOG.info("Inserindo produto");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch(Exception e){
            LOG.error("Erro ao inserir o produto");
            e.printStackTrace();
            Error error = new Error("400", "Não foi possivel concluir a ação");
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response update(@PathParam("id") Long id, MangaDTO dto){
        try{
            LOG.infof("Update em produto de id %s", id);
            service.update(id, dto);
            return Response.noContent().build();
        } catch(Exception e){
            LOG.error("Update não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

     @PATCH
     @Path("/image/upload")
     @RolesAllowed({"Admin"})
     @Consumes(MediaType.MULTIPART_FORM_DATA)
     @Produces(MediaType.TEXT_PLAIN)
     public Response insertImage(@MultipartForm MangaImageForm form){
         //String imageName;
         Long id = form.getId();
         try{
             LOG.infof("Inserindo imagem ao produto de id %s", id);
             String imageName = fileService.save(form.getNomeImagem(), form.getImagem());
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
         MangaResponseDTO manga = service.findById(id);
         String imageName = manga.imageName();

         ResponseBuilder response = Response.ok(fileService.getFile(imageName));
         response.header("Content-Disposition", "attachment;filename="+imageName);
         return response.build();
     }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id){
        try{
            LOG.info("Deletando o produto");
            service.delete(id);
            return Response.noContent().build();
        } catch(Exception e){
            LOG.error("Deleção não concluido");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){
        try{
            LOG.info("Buscando todos os produtos");
            return Response.ok(service.getAll(page, pageSize)).build();
        }catch(Exception e){
            LOG.error("Produtos não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        try{
            LOG.infof("Buscando produto de id %s", id); 
            return Response.ok(service.findById(id)).build();
        } catch(NotFoundException e){
            LOG.error("Produto não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/name/{name}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByName(@PathParam("name") String name){

        try{
            LOG.infof("Buscando por %s", name);
            return Response.ok(service.findByName(name)).build();
        } catch(NotFoundException e){
            LOG.info("Produto não encontrado");
            Error error = new Error("404", e.getMessage());
            
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/authorName/{authorName}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByAuthor(@PathParam("authorName") String authorName){

        try{
            LOG.infof("Buscando por %s", authorName);
            return Response.ok(service.findByAuthor(authorName)).build();
        } catch(NotFoundException e){
            LOG.info("Produto não encontrado");
            Error error = new Error("404", e.getMessage());
            
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/publisherName/{publisherName}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByPublisher(@PathParam("publisherName") String publisherName){

        try{
            LOG.infof("Buscando por %s", publisherName);
            return Response.ok(service.findByPublisher(publisherName)).build();
        } catch(NotFoundException e){
            LOG.info("Produto não encontrado");
            Error error = new Error("404", e.getMessage());
            
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/collectionName/{collectionName}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByCollection(@PathParam("collectionName") String collectionName){

        try{
            LOG.infof("Buscando por %s", collectionName);
            return Response.ok(service.findByCollection(collectionName)).build();
        } catch(NotFoundException e){
            LOG.info("Produto não encontrado");
            Error error = new Error("404", e.getMessage());
            
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/genreId/{genreId}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByGenreId(@PathParam("genreId") Long genreId){
        try{
            LOG.infof("Buscando produtos com generos de id %s", genreId); 
            return Response.ok(service.findByGenre(genreId)).build();
        } catch(NotFoundException e){
            LOG.error("Nenhum produto não encontrado");
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