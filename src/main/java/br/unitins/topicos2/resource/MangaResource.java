package br.unitins.topicos2.resource;


import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos2.dto.MangaDTO;
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
    @Path("/image/upload/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({"Admin"})
    public Response salvarImagem(@MultipartForm MangaImageForm form) {
        LOG.info("nome imagem: "+form.getImageName());
        System.out.println("nome imagem: "+form.getImageName());
        
        fileService.save(form.getId(), form.getImageName(), form.getImage());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{imageName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("imageName") String imageName){
        ResponseBuilder response = Response.ok(fileService.getFile(imageName));
        response.header("Content-Disposition", "attachment;filename=" + imageName);
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
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){
        try{
            LOG.info("Buscando todos os produtos");
            return Response.ok(service.getAll(page, pageSize)).build();
        }catch(Exception e){
            LOG.error("Produtos não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/random")
    @RolesAllowed({ "User", "Admin" })
    public Response findAllRandom(@QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){
        try{
            LOG.info("Buscando todos os produtos");
            return Response.ok(service.getAllRandom(page, pageSize)).build();
        }catch(Exception e){
            LOG.error("Produtos não encontrados");
            e.printStackTrace();
            Error error = new Error("400", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        }
    }

    @GET
    @Path("/{id}")
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
    public Response findByName(@PathParam("name") String name, @QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){

        try{
            LOG.infof("Buscando por %s", name);
            return Response.ok(service.findByName(name, page, pageSize)).build();
        } catch(NotFoundException e){
            LOG.error("Produto não encontrado");
            e.printStackTrace();
            Error error = new Error("404", e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/authorName/{authorName}")
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
    @Path("/search/colecao/{id}")
    public Response findByCollection(@PathParam("id") Long id, @QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){

        try{
            LOG.infof("Buscando por produtos com a coleção de id %s", id);
            return Response.ok(service.findByCollection(id, page, pageSize)).build();
        } catch(NotFoundException e){
            LOG.info("Produto não encontrado");
            Error error = new Error("404", e.getMessage());
            
            return Response.status(Status.NOT_FOUND).entity(error).build();
        }
    }

    @GET
    @Path("/search/genero/{id}")
    public Response findByGenreId(@PathParam("id") Long id, @QueryParam("page") @DefaultValue("0") int page,
    @QueryParam("pageSize") @DefaultValue("100") int pageSize){
        try{
            LOG.infof("Buscando produtos com generos de id %s", id); 
            return Response.ok(service.findByGenre(id, page, pageSize)).build();
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

    @GET
    @Path("/count/name/{name}")
    public long count(@PathParam("name") String name){
        return service.countByName(name);
    }

    @GET
    @Path("/count/genre/{genreId}")
    public long countGenre(@PathParam("genreId") Long genreId){
        return service.countByGenre(genreId);
    }

    @GET
    @Path("/count/collection/{id}")
    public long countCollection(@PathParam("id") Long id){
        return service.countByCollection(id);
    }
}