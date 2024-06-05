package br.unitins.topicos2.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos2.form.MangaImageForm;
import br.unitins.topicos2.service.UserFileService;
import br.unitins.topicos2.service.UserService;
import br.unitins.topicos2.dto.UserResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserLogadoResource {

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    @Inject
    UserFileService fileService;

    @GET
    @RolesAllowed({ "User", "Admin" })
    @Path("/current")
    public Response getUser() {

        // obtendo o login pelo token jwt
        String login = jwt.getSubject();

        return Response.ok(userService.findByEmail(login)).build();

    }

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm MangaImageForm form) {
        UserResponseDTO userDTO = userService.findById(form.getId());

        LOG.info("nome imagem: "+form.getImageName());
        System.out.println("nome imagem: "+form.getImageName());
        
        fileService.save(form.getId(), form.getImageName(), form.getImage());
        userDTO = userService.updateImageName(userDTO.id(), form.getImageName());
        LOG.info("Imagem alterada");
        return Response.ok(userDTO).build();
    }

    @GET
    @Path("/image/download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id")Long id) {
        
        LOG.info("Buscando imagem do usuario");
        UserResponseDTO user = userService.findById(id);
        String imageName = user.imageName();
        
        ResponseBuilder response = Response.ok(fileService.getFile(imageName));
        response.header("Content-Disposition", "attachment;filename="+imageName);
        return response.build();
    }
}
