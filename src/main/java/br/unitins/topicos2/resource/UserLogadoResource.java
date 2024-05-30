package br.unitins.topicos2.resource;

import java.io.File;
import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos2.form.UserImageForm;
import br.unitins.topicos2.service.UserFileService;
import br.unitins.topicos2.service.UserService;
import br.unitins.topicos2.application.Error;
import br.unitins.topicos2.dto.UserResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

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
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveimage(@MultipartForm UserImageForm form){
        String imageName;
        try {
            LOG.info("Inserindo imagem");
            imageName = fileService.save(form.getImageName(), form.getImage());
            String login = jwt.getSubject();
            LOG.info("Alterando imagem do usuario");
            UserResponseDTO userDTO = userService.findByEmail(login);
            userDTO = userService.updateImageName(userDTO.id(), imageName);
            LOG.info("Imagem alterada");
            return Response.ok(userDTO).build();
        } catch (IOException e) {
            LOG.error("Erro ao inserir imagem");
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }

    }

    @GET
    @Path("/image/download")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download() {
        String login = jwt.getSubject();
        LOG.info("Login do usuário: " + login);

        UserResponseDTO user = userService.findByEmail(login);
        if (user == null) {
            LOG.error("Usuário não encontrado: " + login);
        return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String imageName = user.imagenamem();
        LOG.info("Nome da imagem: " + imageName);

        File file = fileService.getFile(imageName);
        if (file == null) {
            LOG.error("Arquivo não encontrado: " + imageName);
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment;filename=" + imageName);
        return response.build();
    }
}
