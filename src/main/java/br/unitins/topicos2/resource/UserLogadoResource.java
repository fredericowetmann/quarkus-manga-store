package br.unitins.topicos2.resource;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/userlogado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    @Inject
    UserFileService fileService;

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response getUser() {

        // obtendo o login pelo token jwt
        String login = jwt.getSubject();

        return Response.ok(userService.findByLogin(login)).build();

    }

    @PATCH
    @Path("/upload/imagem")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm UserImageForm form){
        String nameImagem;
        try {
            nameImagem = fileService.save(form.getNameImagem(), form.getImagem());
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }

        String login = jwt.getSubject();
        UserResponseDTO userDTO = userService.findByLogin(login);
        userDTO = userService.updateNameImagem(userDTO.id(), nameImagem);

        return Response.ok(userDTO).build();

    }

    @GET
    @Path("/download/imagem/{nameImagem}")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nameImagem") String nameImagem) {
        ResponseBuilder response = Response.ok(fileService.getFile(nameImagem));
        response.header("Content-Disposition", "attachment;filename="+nameImagem);
        return response.build();
    }

}
