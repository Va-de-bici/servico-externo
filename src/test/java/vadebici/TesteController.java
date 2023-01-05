package vadebici;

import static org.mockito.Mockito.*;
import vadebici.util.JavalinApp;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import vadebici.controller.CartaoCreditoController;
import vadebici.controller.EmailController;
import vadebici.dom.CartaoCredito;
import vadebici.dom.Email;
import vadebici.form.CartaoCreditoForm;
import vadebici.pagamento.Cobranca; 
import vadebici.pagamento.Pagamento;
import vadebici.pagamento.TransacaoCielo;
import vadebici.service.ServiceEmail;
import vadebici.util.JavalinApp;
import vadebici.util.Segredos;
import vadebici.util.Validacao;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.JsonNode;
import static vadebici.modelo.CartaoCreditoModelo.*;

class ControllerTest {
	private static JavalinApp app = new JavalinApp();
	private static final String BASE_URL = "http://localhost:7010";


	@BeforeAll
	static void init() {
		app.start(7010);
	}

	@AfterAll
	static void afterAll() {
		app.stop();
	}

	/*@Test
	void TransacaoCieloTest() {
		TransacaoCielo cielo = new TransacaoCielo("95061429-4514-431d-8585-befe1ae162da");
		String merchantOrderId = "95061429-4514-431d-8585-befe1ae162da";
		assertEquals(merchantOrderId, cielo.MerchantOrderId);
	}*/

	@Test
	void getValorPagamentoTest() {
		Pagamento payment = new Pagamento();
		payment.setAmount(10);
		assertEquals(10, payment.getAmount());
	}

	@Test
	void getTipoPagamentoTest() {
		Pagamento payment = new Pagamento();
		payment.setType("credito");
		String tipo = "credito";
		assertEquals(tipo, payment.getType());
	}

	@Test
	void getInstallmentsTest() {
		Pagamento payment = new Pagamento();
		payment.setInstallments(1);
		int installment = 1;
		assertEquals(installment, payment.getInstallments());
	}

	@Test
	void cartaoCreditoFormTest() {
		CartaoCreditoForm cc = new CartaoCreditoForm();
		CartaoCreditoForm ccForm = new CartaoCreditoForm("Fulano Teste", "0000.0000.0000.0004", "10/2025", "333");
		ccForm.deserialize();
		assertEquals("333", ccForm.cvv);
	}

	@Test
	void cartaoCreditoTest() {
		CartaoCredito cc = new CartaoCredito("Fulano Teste", "0000.0000.0000.0004", "12/2024", "321");
		cc.setCvv("123");
		String cvv = cc.getCvv();
		cc.setNumero("0000.0000.0000.0001");
		String numero = cc.getNumero();
		cc.setNomeTitular("Fulano Tester");
		String nome = cc.getNomeTitular();
		cc.setValidade("12/2025");
		String validade = cc.getValidade();
		assertEquals("123", cvv);
	}

	@Test
	void emailTest() {
		Email e = new Email("email@mail.com", "Teste");
		e.setEmail("teste@mail.com");
		String email = e.getEmail();
		e.setMensagem("Teste teste");
		String mensagem = e.getMensagem();
		assertEquals("Teste teste", mensagem);
	}

	@Test
	void testGetBand() {
		CartaoCredito cartao = new CartaoCredito();
		cartao.setNumero("4556346904266753");
		assertEquals("Visa", cartao.getBand());
	}

	@Test
	void getValorCobranca() {
		CobrancaD cobranca = new Cobranca("3fa85f64-5717-4562-b3fc-2c963f66afa6", 10);
		cobranca.setValor(15);
		int valor = cobranca.getValor();
		assertEquals(15, valor);
	}

	@Test
	void getCiclistaIdCobranca() {
		CobrancaD cobranca = new Cobranca("a1b2", 10);
		cobranca.setCiclistaId("3fa85f64-5717-4562-b3fc-2c963f66afa6");
		String id = cobranca.getCiclistaId();
		assertEquals("3fa85f64-5717-4562-b3fc-2c963f66afa6", id);
	}


	/*@Test
	void enviaEmail_QuandoBemSucedido() {
        Email email = new Email();
        email.setEmail("bruno@unirio.br");
        email.setMensagem("teste externo");
        HttpResponse<String> response = Unirest.post(BASE_URL+"/enviarEmail")
                .body(email)
                .asString();

        assertEquals(200, response.getStatus());
        assertEquals("", response.getBody());
    }

	@Test
    void validarCartao_RetornaVerdadeiro_QuandoBemSucedido() {
        CartaoCredito cartao = getCartao();
        HttpResponse<String> response = Unirest.post(BASE_URL+"/validaCartaoDeCredito")
                .body(cartao)
                .asString();

        assertEquals(200, response.getStatus());
        assertEquals("VERIFIED", response.getBody());
    }*/


}


