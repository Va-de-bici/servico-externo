/*package vadebici.controller;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vadebici.dom.Email;
import vadebici.util.JavalinApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailControllerTeste {
    private final static JavalinApp app = new JavalinApp();
    private static final String BASE_URL = "http://localhost:7010";

    @BeforeAll
    static void init() {
        app.start(7010);
    }

    @AfterAll
    static void stop() {
        app.stop();
    }

    @Test
    void enviaEmail_QuandoCerto() {
        Email email = new Email();
        email.setEmail("bruno@unirio.br");
        email.setMensagem("teste pm externo");
        HttpResponse<String> response = Unirest.post(BASE_URL+"/enviarEmail")
                .body(email)
                .asString();

        assertEquals(200, response.getStatus());
        assertEquals("", response.getBody());
    }
}

*/