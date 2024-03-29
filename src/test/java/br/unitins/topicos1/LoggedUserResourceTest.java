package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.jboss.logging.Logger;
import org.jose4j.http.Get;

import br.unitins.topicos2.dto.CompleteUserDTO;
import br.unitins.topicos2.dto.CompleteUserResponseDTO;
import br.unitins.topicos2.dto.EmailDTO;
import br.unitins.topicos2.dto.PhoneDTO;
import br.unitins.topicos2.dto.PhoneResponseDTO;
import br.unitins.topicos2.dto.UpdatePasswordDTO;
import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import br.unitins.topicos2.dto.UsernameDTO;
import br.unitins.topicos2.repository.UserRepository;
import br.unitins.topicos2.resource.AuthResource;
import br.unitins.topicos2.service.HashService;
import br.unitins.topicos2.service.JwtService;
import br.unitins.topicos2.service.UserService;

@QuarkusTest
public class LoggedUserResourceTest {

    @Inject
    UserService userService;

    @Inject
    UserRepository userRepository;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @Test
    public void testGetUserNotLogged(){
        given().when().get("/loggedUser").then().statusCode(401);
    }

    @Test
    public void testGetUser(){
        UserDTO dto = new UserDTO("fulanozx", "fulano1@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        String token = jwtService.generateJwt(userService.findByEmail("fulano1@mail.com"));

        given().header("Authorization", "Bearer " + token).when().get("/loggedUser").then().statusCode(200);
    }

    @Test
    public void testUpdateUsername(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("fulanocb", "fulano2@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Inserindo o novo username
        UsernameDTO usernameDTO = new UsernameDTO("teo");

        //Pegando Id
        Long idUser = userService.findByEmail("fulano2@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(usernameDTO).when().patch("/loggedUser/update/username/").then().statusCode(204);

        //verificando os dados
        UserResponseDTO user = userService.findById(idUser);
        //assertThat(user.username(), is(usernameDTO.username()));
    }

    @Test
    public void testUpdateUsernameNullValue(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("chelsea", "chelsea@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Inserindo o novo username
        UsernameDTO usernameDTO = new UsernameDTO(null);

        //Pegando Id
        Long idUser = userService.findByEmail("chelsea@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(usernameDTO).when().patch("/loggedUser/update/username/").then().statusCode(400);

        //verificando os dados
        UserResponseDTO user = userService.findById(idUser);
        //assertThat(user.username(), is(usernameDTO.username()));
    }


    @Test
    public void testUpdateEmail(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("fulanobn", "fulanoabc@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Inserindo o novo email
        EmailDTO emailDTO = new EmailDTO("novofulanoabc@mail.com");
        
        Long idUser = userService.findByEmail("fulanoabc@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(emailDTO).when().patch("/loggedUser/update/email/").then().statusCode(204);

        //verificando os dados
        UserResponseDTO user = userService.findById(idUser);
        //assertThat(usernameAtual, is(usernameDTO.username()));
    }

    @Test
    public void testUpdateEmailNullValue(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("roman", "roman@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Inserindo o novo email
        EmailDTO emailDTO = new EmailDTO(null);
        
        Long idUser = userService.findByEmail("roman@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(emailDTO).when().patch("/loggedUser/update/email/").then().statusCode(400);

        //verificando os dados
        UserResponseDTO user = userService.findById(idUser);
        //assertThat(usernameAtual, is(usernameDTO.username()));
    }

    @Test
    public void testUpdatePassword(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("fulanonm", "fulano4@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Inserindo a senha atual e a nova senha
        UpdatePasswordDTO passwordDTO = new UpdatePasswordDTO(hashService.getHashPassword("12345"), "senhateste");

        //pegando Id do usuario
        Long idUser = userService.findByEmail("fulano4@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(passwordDTO).when().patch("/loggedUser/update/password/").then().statusCode(204);

        //verificando os dados
        UserResponseDTO user = userService.findById(idUser);
        //assertThat(usernameAtual, is(usernameDTO.username()));
    }

    //Validação de segurança
    @Test
    public void testUpdatePasswordWrongPassword(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("cassia", "cassia@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Inserindo a senha atual e a nova senha
        UpdatePasswordDTO passwordDTO = new UpdatePasswordDTO(hashService.getHashPassword("1234"), "senhateste");

        //pegando Id do usuario
        Long idUser = userService.findByEmail("cassia@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(passwordDTO).when().patch("/loggedUser/update/password/").then().statusCode(403);

        //verificando os dados
        UserResponseDTO user = userService.findById(idUser);
        //assertThat(usernameAtual, is(usernameDTO.username()));
    }

    //Validação valor não valido
    @Test
    public void testUpdatePasswordNullvalue(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("ze", "ze@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Inserindo a senha atual e a nova senha
        UpdatePasswordDTO passwordDTO = new UpdatePasswordDTO(hashService.getHashPassword("12345"), null);

        //pegando Id do usuario
        Long idUser = userService.findByEmail("ze@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(passwordDTO).when().patch("/loggedUser/update/password/").then().statusCode(400);

        //verificando os dados
        UserResponseDTO user = userService.findById(idUser);
        //assertThat(usernameAtual, is(usernameDTO.username()));
    }


    @Test
    public void testInsertUsername(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO(null, "fulano7@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //pegando Id do usuario
        Long idUser = userService.findByEmail("fulano7@mail.com").id();

        //Passando o username
        UsernameDTO username = new UsernameDTO("marcio");
        
        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(username).when().patch("/loggedUser/complete/username/").then().statusCode(200);
    }

    @Test
    public void testInsertUsernameNullValue(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO(null, "sami@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //pegando Id do usuario
        Long idUser = userService.findByEmail("sami@mail.com").id();

        //Passando o username
        UsernameDTO username = new UsernameDTO(null);
        
        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(username).when().patch("/loggedUser/complete/username/").then().statusCode(400);
    }

    @Test
    public void testInsertUsernameAlreadyExistent(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("fulanoas", "fulano8@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //pegando Id do usuario
        Long idUser = userService.findByEmail("fulano8@mail.com").id();

        //Passando o username
        UsernameDTO username = new UsernameDTO("marciano");
        
        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(username).when().patch("/loggedUser/complete/username/").then().statusCode(400);
    }

    @Test
    public void testGetCompleteUserByEmailNotLogged(){
        given().when().get("/loggedUser/find/completeUser/").then().statusCode(401);
    }

    @Test
    public void testGetCompleteuserByEmail(){
        UserDTO dto = new UserDTO("fulanosd", "fulano9@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        String token = jwtService.generateJwt(userService.findByEmail("fulano9@mail.com"));

        given().header("Authorization", "Bearer " + token).when().get("/loggedUser/find/completeUser/").then().statusCode(200);
    }
    
    @Test
    public void testCompleteUser(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("ciclano", "ciclano@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Completando Dados
        CompleteUserDTO userDTO = new CompleteUserDTO("Nome Aleatorio", "5555433432", 1);
        
        Long idUser = userService.findByEmail("ciclano@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(userDTO).when().patch("/loggedUser/complete/register/").then().statusCode(204);
    }

    @Test
    public void testCompleteUserNotLogged(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("ciclanodf", "ciclano1@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Completando Dados
        CompleteUserDTO userDTO = new CompleteUserDTO("Nome Aleatorio", "5555433432", 1);
        
        Long idUser = userService.findByEmail("ciclano1@mail.com").id();

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().contentType(ContentType.JSON).body(userDTO).when().patch("/loggedUser/complete/register/").then().statusCode(401);
    }

    @Test
    public void testCompleteUserAlreadyRegistered(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("ciclanofg", "ciclano2@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Completando Dados
        CompleteUserDTO userDTO = new CompleteUserDTO("Nome Aleatorio", "555543543432", 1);
        CompleteUserDTO userDTO2 = new CompleteUserDTO("Nome Aleatorio 2", "55554df33432", 1);
        
        //pegando o id
        Long idUser = userService.findByEmail("ciclano2@mail.com").id();

        CompleteUserResponseDTO userTest2 = userService.completeUser(idUser, userDTO);

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON).body(userDTO2).when().patch("/loggedUser/complete/register/").then().statusCode(400);
    }

    @Test
    public void testGetCompleteuserByEmailAfterRegister(){
        //Inserindo novo usuario
        UserDTO dto = new UserDTO("ciclanogh", "ciclano3@mail.com", hashService.getHashPassword("12345"), 2);
        UserResponseDTO userTest = userService.insert(dto);

        //Completando Dados
        CompleteUserDTO userDTO = new CompleteUserDTO("Nome Aleatorio", "5555767676433432", 1);
        CompleteUserDTO userDTO2 = new CompleteUserDTO("Nome Aleatorio 2", "555545df33432", 1);
        
        //pegando o id
        Long idUser = userService.findByEmail("ciclano3@mail.com").id();

        CompleteUserResponseDTO userTest2 = userService.completeUser(idUser, userDTO);

        //gerando token para autorização
        String token = jwtService.generateJwt(userService.findById(idUser));

        //Testando status code
        given().header("Authorization", "Bearer " + token).when().get("/loggedUser/find/completeUser/").then().statusCode(200);
    }
}
