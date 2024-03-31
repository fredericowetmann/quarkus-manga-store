package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.topicos2.dto.PhoneDTO;
import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.service.UserService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class UserResourceTest {

    @Inject
    UserService userService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/users")
          .then()
             .statusCode(200);
    }

    // @Test
    // public void testInsert() {
    //     List<PhoneDTO> phones = new ArrayList<PhoneDTO>();
    //     phones.add(new PhoneDTO("63", "5555-5555"));

    //     UserDTO dto = new UserDTO(
    //         "Mark Zuckerberg Insert",
    //         "marquinho",
    //         "333",
    //         phones
    //     );

    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(dto)
    //         .when().post("/users")
    //         .then()
    //         .statusCode(201)
    //         .body(
    //             "id", notNullValue(),
    //             "nome", is("Mark Zuckerberg Insert"),
    //             "login", is("marquinho")
    //         );
    // }

    // @Test
    // public void testUpdate() {
    //     List<PhoneDTO> phones = new ArrayList<PhoneDTO>();
    //     phones.add(new PhoneDTO("63", "5555-5555"));

    //     UserDTO dto = new UserDTO(
    //         "Mark Zuckerberg Update",
    //         "marquinho",
    //         "333",
    //         phones
    //     );

    //     // inserindo um user
    //     UserResponseDTO userTest = userService.insert(dto);
    //     Long id = userTest.id();

    //     UserDTO dtoUpdate = new UserDTO(
    //         "Mark Zuckerberg",
    //         "mark",
    //         "555",
    //         phones
    //     );

    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(dtoUpdate)
    //         .when().put("/users/"+ id)
    //         .then()
    //         .statusCode(204);

    //     // verificando a alteracao

    //     System.out.println(id);
    //     System.out.println(id);
    //     System.out.println(id);
    //     System.out.println(id);
    //     UserResponseDTO usu = userService.findById(4l);
    //     assertThat(usu.nome(), is("Mark Zuckerberg"));
    //     assertThat(usu.login(), is("mark"));

    // }

}