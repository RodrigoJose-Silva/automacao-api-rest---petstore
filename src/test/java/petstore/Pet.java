//1 - Pacote
package petstore;
//2-Biblioteca


import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

//3- Classe
public class Pet {
    //3.1 - Atributos
    String urli = "https://petstore.swagger.io/v2/pet"; //url da entidade (API) Pet


    //3.2 - Métodos e Funções
    public String lerJson (String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Incluir - Create - Post
    @Test  // Identifica o método ou função como "TESTE" para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //Sintaxe Gherkin
        // DADO - QUANDO - ENTÃO
        //Given - When - Then

        given()//DADO
                .contentType("application/json") //comum em API REST - antigas eram "text / xml"
                .log().all()
                .body(jsonBody)
        .when() //QUANDO
                .post(urli)
        .then() //ENTÃO
                .log().all()
                .statusCode(200)
                .body("name", is("Pandora"))
                .body("status", is("available"))
        ;
    }

}
