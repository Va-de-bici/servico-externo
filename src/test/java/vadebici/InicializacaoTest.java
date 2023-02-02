package vadebici;

import io.javalin.Javalin;
import org.junit.jupiter.api.Test;
import vadebici.util.JavalinApp;
import static org.junit.jupiter.api.Assertions.*;


public class InicializacaoTest {

    private final JavalinApp javalinApp = new JavalinApp();

    @Test
    void runServer() {
        Javalin app = javalinApp.start(9000);

        assertNotNull(app);

        javalinApp.stop();
    }
}
