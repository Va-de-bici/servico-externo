package vadebici.util;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJson;
import vadebici.controller.EmailController;
import vadebici.controller.CartaoCreditoController;
import vadebici.controller.FilaCobrancaController;
import vadebici.exception.GenericApiException;
import vadebici.pagamento.Cobranca;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApp {
    private final Javalin app =
            Javalin.create(config -> config.defaultContentType = "application/json")
                    .routes(() -> {
                        path("/cobranca/:id", ()-> get(Cobranca::getCobranca));
                        path("/cobranca",  ()-> post(Cobranca::realizaCobranca));
                        path("/filaCobranca",  () -> post(FilaCobrancaController::filaCobranca));
                        path("/enviarEmail", () -> post(EmailController::enviaEmail));
                        path("/validaCartaoDeCredito", () -> post(CartaoCreditoController::validaCartaoCredito));

                    }).error(500, ctx -> {
                        GenericApiException ex = new GenericApiException(500, "Ocorreu um erro durante a operação");
                        ctx.result(JavalinJson.toJson(ex));
                    }).error(404, ctx -> {
                        if (ctx.attribute("handle") == null) {
                            GenericApiException ex = new GenericApiException(404, "Página não encontrada");
                            ctx.result(JavalinJson.toJson(ex));
                        }
                    })
                    .exception(GenericApiException.class, (e, ctx) -> {
                        ctx.attribute("handle", true);
                        String response = JavalinJson.getToJsonMapper().map(e);
                        ctx.status(e.getCodigo());
                        ctx.result(response);
                    });


    public void start(int port) {
        this.app.start(port);
    }

    public void stop() {
        this.app.stop();
    }
}



