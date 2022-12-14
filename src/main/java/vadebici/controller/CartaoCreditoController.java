package vadebici.controller;

import java.util.ArrayList;

import org.json.JSONObject;

import vadebici.form.CartaoCreditoForm;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import vadebici.dom.CartaoCredito;
import vadebici.pagamento.Cobranca;


public class CartaoCreditoController {

	public static boolean validaCartaoCredito(Context ctx) {
		CartaoCredito cartao = ctx.bodyAsClass(CartaoCreditoForm.class).deserialize();
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

