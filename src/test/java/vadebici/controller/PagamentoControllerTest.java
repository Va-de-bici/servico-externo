package vadebici.controller;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.*;
import vadebici.db_in_memory.Database;
import vadebici.dom.Cartao;
import vadebici.dom.Cobranca;
import vadebici.dom.CobrancaRequisicao;
import vadebici.util.JavalinApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static vadebici.models.PagamentoModelos.*;

public class PagamentoControllerTest {
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

    /*@Test
    void realizarCobranca_RetornaCobranca_QuandoBemSucedido() {
        CobrancaRequisicao cobrancaRequisicao = getCobrancaRequisicao();
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL+"/cobranca")
                .body(cobrancaRequisicao)
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(200, response.getStatus());
        assertEquals(cobrancaRequisicao.getValor(), jsonResponse.get("valor"));
        assertEquals(cobrancaRequisicao.getCiclista(), jsonResponse.get("ciclista"));
        assertEquals("APPROVED", jsonResponse.get("status"));
        assertNotNull(jsonResponse.get("id"));
        assertNotNull(jsonResponse.get("horaSolicitacao"));
        assertNotNull(jsonResponse.get("horaFinalizacao"));
    }*/

    @Test
    void buscarCobranca_RetornaCobranca_QuandoBemSucedido() {
        Cobranca cobranca = getCobranca();
        DateFormat dateFormat = getDateFormat();
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL+"/cobranca/123")
                .asJson();
        JSONObject jsonResponse = response.getBody().getObject();

        assertEquals(cobranca.getId(), jsonResponse.get("id"));
        assertEquals(cobranca.getValor(), jsonResponse.get("valor"));
        assertEquals(cobranca.getCiclista(), jsonResponse.get("ciclista"));
        assertEquals(cobranca.getStatus(), jsonResponse.get("status"));



    }

    /*@Test
    void validarCartao_RetornaVerdadeiro_QuandoBemSucedido() {
        Cartao cartao = getCartao();
        HttpResponse<String> response = Unirest.post(BASE_URL+"/validaCartaoDeCredito")
                .body(cartao)
                .asString();

        assertEquals(200, response.getStatus());
    }*/

    DateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }
}
