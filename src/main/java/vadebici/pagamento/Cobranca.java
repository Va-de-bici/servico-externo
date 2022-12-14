package vadebici.pagamento;

import vadebici.dom.CartaoCredito;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class Cobranca {
	private CartaoCredito cartao;

	private static final String MERCHANTID = "95061429-4514-431d-8585-befe1ae162da";
	private static final String MERCHANTKEY = "XQHDFODQYLWBYSJEEXTCRZUJGKTYUYIESBULOZIK";
	private static final String REQUISICAO = "https://apisandbox.cieloecommerce.cielo.com.br/1/sales/";

	public HttpResponse<JsonNode> validaCartaoCredito(CartaoCredito cartao) {
		TransacaoCielo transacao = new TransacaoCielo("1");

		transacao.Payment.setAmount(1);

		transacao.Payment.setInstallments(1);

		transacao.Payment.setType("CreditCard");

		transacao.Payment.getCreditCard().setBrand("Master");

		transacao.Payment.getCreditCard().setCardNumber(cartao.getNumero());

		transacao.Payment.getCreditCard().setExpirationDate(cartao.getValidade());

		transacao.Payment.getCreditCard().setSecurityCode(cartao.getCvv());

		HttpResponse<JsonNode> response = Unirest.post(REQUISICAO)
				.header("Content-Type", "application/json")
				.header("MerchantKey", MERCHANTKEY)
				.header("MerchantId", MERCHANTID)
				.body(transacao)
				.asJson();

		return response;
	}
}

