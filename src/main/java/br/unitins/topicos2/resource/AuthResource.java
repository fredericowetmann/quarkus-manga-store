package br.unitins.topicos2.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos2.dto.LoginDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import br.unitins.topicos2.service.HashService;
import br.unitins.topicos2.service.JwtService;
import br.unitins.topicos2.service.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UserService service;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    public Response login(@Valid LoginDTO dto) {
        LOG.infof("Iniciando a autenticacao do %s", dto.login());

        String hashPassword = hashService.getHashPassword(dto.password());

        LOG.info("Hash da senha gerado.");

        LOG.debug(hashPassword);

        UserResponseDTO result = service.findByLoginAndPassword(dto.login(), hashPassword);

        if (result != null)
            LOG.info("Login e senha corretos.");
        else
            LOG.info("Login e senha incorretos.");
        
        String token = jwtService.generateJwt(result);

        LOG.info("Finalizando o processo de login.");

        return Response.ok().header("Authorization", token).build();
    }
  
}
