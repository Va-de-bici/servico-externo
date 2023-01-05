package vadebici.controller;

import java.util.ArrayList;

import vadebici.form.CartaoCreditoForm;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import vadebici.dom.CartaoCredito;
import vadebici.pagamento.Cobranca;
import io.javalin.plugin.json.JavalinJson;


public class CartaoCreditoController {

	public CartaoCreditoController(){}

	public CartaoCredito retornaCartaoDeCreditoIntegracao() {
		CartaoCredito card = new CartaoCredito("Bruno Bastos", "4556346904266753", "03/2024", "118");
		return card;
	}


	public static boolean validaCartaoCredito(Context ctx) {
		String body = ctx.body();
		CartaoCredito cartao = JavalinJson.getFromJsonMapper().map(body, CartaoCredito.class);
		Cobranca validacao = new Cobranca();
		HttpResponse<JsonNode> result = validacao.validaCartaoCredito(cartao);

		if (result.getStatus() == 201) {
			ctx.status(200);
			return true;
		} else if (result.getStatus() == 402) {
			ctx.status(402);
			return false;
		} else if (result.getStatus() == 422) {
			ctx.status(422);
			return false;
		}
		return false;
	}
	
}

