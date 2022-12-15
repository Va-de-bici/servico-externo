package vadebici;

import io.javalin.Javalin;
import org.junit.jupiter.api.Test;
import vadebici.util.JavalinApp;

import static org.junit.jupiter.api.Assertions.*;

public class InitTest {

    private final JavalinApp javalinApp = new JavalinApp();

    @Test
    void inicializaServidor_QuandoBemSucedido() {
        javalinApp.start(9000);
        javalinApp.stop();
    }
}

