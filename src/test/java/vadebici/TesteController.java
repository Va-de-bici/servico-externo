package vadebici;

import static org.mockito.Mockito.*;
import main.util.JavalinApp;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import controller.CartaoCreditoController;
import controller.EmailController;
import dom.CartaoCredito;
import dom.Email;
import form.CartaoCreditoForm;
import pagamento.Cobranca;
import pagamento.Pagamento;
import pagamento.TransacaoCielo;
import service.ServiceEmail;
import util.JavalinApp;
import util.Segredos;
import util.Validacao;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.JsonNode;

class ControllerTest {
	private static JavalinApp app = new JavalinApp();

	@BeforeAll
	static void init() {
		app.start(7010);
	}

	@AfterAll
	static void afterAll() {
		app.stop();
	}

	@Test
	void cieloIntegracaoTest() {
		CieloIntegracao cielo = new CieloIntegracao("95061429-4514-431d-8585-befe1ae162da");
		String merchantOrderId = "95061429-4514-431d-8585-befe1ae162da";
		assertEquals(merchantOrderId, cielo.MerchantOrderId);
	}

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
	void cartaoDeCreditoFormTest() {
		CartaoDeCreditoForm cc = new CartaoDeCreditoForm();
		CartaoDeCreditoForm ccForm = new CartaoDeCreditoForm("Fulano Teste", "0000.0000.0000.0004", "10/2025", "333");
		ccForm.desserialize();
		assertEquals("333", ccForm.cvv);
	}

	@Test
	void cartaoDeCreditoTest() {
		CartaoDeCredito cc = new CartaoDeCredito("Fulano Teste", "0000.0000.0000.0004", "12/2024", "321");
		cc.setCvv("123");
		String cvv = cc.getCvv();
		cc.setNumero("0000.0000.0000.0001");
		String numero = cc.getNumero();
		cc.setNomeTitular("Fulano Tester");
		String nome = cc.getNomeTitular();
		cc.setValidade("12/2025");
		String validade = cc.getValidade();
		cc.setId("1");
		String id = cc.getId();
		assertEquals("1", id);
	}

	@Test
	void emailTest() {
		Email e = new Email("email@mail.com", "Teste");
		e.setId("1");
		String id = e.getId();
		e.setEmail("teste@mail.com");
		String email = e.getEmail();
		e.setMensagem("Teste teste");
		String mensagem = e.getMensagem();
		assertEquals("Teste teste", mensagem);
	}


}


