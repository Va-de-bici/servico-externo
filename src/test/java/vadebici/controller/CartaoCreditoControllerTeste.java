package vadebici.controller;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.*;
import vadebici.db_in_memory.Database;
import vadebici.dom.CartaoCredito;
import vadebici.util.JavalinApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class CartaoCreditoControllerTeste {
    private final static JavalinApp app = new JavalinApp();
    private static final String BASE_URL = "http://localhost:7010";

    @BeforeAll
    static void init() {
        app.start(7010);
    }

    @AfterAll
    static void stop() {
        app.stop();
        Database.limpar();
    }

    @BeforeEach
    void clean() {
        Database.carregarDados();
    }

    @Test
    void validarCartao_RetornaVerdadeiro_QuandoBemSucedido() {
        CartaoCredito cartao = getCartao();
        HttpResponse<String> response = Unirest.post(BASE_URL+"/validaCartaoDeCredito")
                .body(cartao)
                .asString();

        assertEquals(200, response.getStatus());
        assertEquals("VERIFIED", response.getBody());
    }

    @Test
    void validarCartao_RetornaErro_QuandoNumeroInvalido() {
        CartaoCredito cartao = getCartao();
        cartao.setNumero("000000000000000");
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL+"/validaCartaoDeCredito")
                .body(cartao)
                .asJson();

        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(422, response.getStatus());
        assertEquals(422, jsonResponse.get("codigo"));
        assertEquals("card_number is invalid : \"card_number\" with value \"000000000000000\" matches the inverted zeros pattern", jsonResponse.get("mensagem"));
        assertNotNull(jsonResponse.get("id"));
    }


}

