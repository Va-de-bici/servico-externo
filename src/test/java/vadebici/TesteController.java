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
import vadebici.controller.FilaCobrancaController;
import vadebici.dom.CartaoCredito;
import vadebici.dom.Email;
import vadebici.dom.CobrancaD;
import vadebici.dom.FilaCobranca;
import vadebici.form.CartaoCreditoForm;
import vadebici.form.EmailForm;
import vadebici.form.CobrancaForm;
import vadebici.pagamento.Cobranca; 
import vadebici.pagamento.Pagamento;
import vadebici.pagamento.TransacaoCielo;
//import vadebici.service.ServiceEmail;
import vadebici.util.JavalinApp;
//import vadebici.util.Segredos;
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
		CartaoCreditoForm ccForm = new CartaoCreditoForm("Fulano Teste", "0000.0000.0000.0004", "10/2027", "123");
		ccForm.deserialize();
		assertEquals("123", ccForm.cvv);
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
	void emailFormTest() {
		EmailForm ef = new EmailForm();
		EmailForm email = new EmailForm("Teste", "email@mail.com");
		email.deserialize();
		assertEquals("Teste", email.message);
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
		CobrancaD cobranca = new CobrancaD("3fa85f64-5717-4562-b3fc-2c963f66afa6", 10);
		cobranca.setValor(15);
		int valor = cobranca.getValor();
		assertEquals(15, valor);
	}

	@Test
	void getCiclistaIdCobranca() {
		CobrancaD cobranca = new CobrancaD("a1b2", 10);
		cobranca.setCiclistaId("3fa85f64-5717-4562-b3fc-2c963f66afa6");
		String id = cobranca.getCiclistaId();
		assertEquals("3fa85f64-5717-4562-b3fc-2c963f66afa6", id);
	}

	void cobrancaFormTest() {
		CobrancaForm cf = new CobrancaForm();
		CobrancaForm cobForm = new CobrancaForm("3fa85f64-5717-4562-b3fc-2c963f66afa6", "Pendente", "", "", 10,
				"3fa85f64-5717-4562-b3fc-2c963f66afa6");
		CobrancaForm cobrancaForm = new CobrancaForm("3fa85f64-5717-4562-b3fc-2c963f66afa6", 1);
		CobrancaD cobranca = new CobrancaD("3fa85f64-5717-4562-b3fc-2c963f66afa6", "Pendente", "", "", 10,
				"3fa85f64-5717-4562-b3fc-2c963f66afa6");
		CobrancaD resultado = cobForm.unserialize();
		CobrancaD cobrancaPost = cobForm.deserializePostCobranca();

		assertEquals(cobranca.getId(), resultado.getId());
	}

	@Test
	void filaCobrancaTest() {
		FilaCobranca fc = new FilaCobranca();
		Queue<CobrancaD> filaDeCobrancas = new LinkedList<>();
		Queue<CobrancaD> filaCobrancas = new LinkedList<>();
		for (CobrancaD c : filaCobrancas) {
			filaCobrancas.add(c);
		}
		FilaCobranca filaC = new FilaCobranca(filaDeCobrancas);
		CobrancaD cobranca = new CobrancaD("3fa85f64-5717-4562-b3fc-2c963f66afa6", "Pendente", "", "", 10,
				"3fa85f64-5717-4562-b3fc-2c963f66afa6");

		filaDeCobrancas.add(cobranca);
		filaC.setQueueCobranca(cobranca);
		assertEquals(filaDeCobrancas, filaC.getQueueCobranca());
	}

	@Test
	void getIdCobranca() {
		CobrancaD cobranca = new CobrancaD("3fa85f64-5717-4562-b3fc-2c963f66afa6", "Pendente", "", "", 10,
				"3fa85f64-5717-4562-b3fc-2c963f66afa6");
		String id = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
		assertEquals(id, cobranca.getId());
	}

	@Test
	void getHoraSolicitacaoCobranca() {
		CobrancaD cobranca = new CobrancaD("3fa85f64-5717-4562-b3fc-2c963f66afa6", "Pendente", "", "", 10,
				"3fa85f64-5717-4562-b3fc-2c963f66afa6");
		cobranca.setHoraSolicitacao("2022-01-14T20:36:00.491Z");
		String horaSolicitacao = cobranca.getHoraSolicitacao();
		assertEquals("2022-01-14T20:36:00.491Z", horaSolicitacao);
	}

	@Test
	void getHoraFinalizacaoCobranca() {
		CobrancaD cobranca = new CobrancaD("Paga", "2022-01-14T20:36:00.491Z", "", 10,
				"3fa85f64-5717-4562-b3fc-2c963f66afa6");
		cobranca.setHoraFinalizacao("2022-01-14T20:38:00.491Z");
		String horaFinalizacao = cobranca.getHoraFinalizacao();
		assertEquals("2022-01-14T20:38:00.491Z", horaFinalizacao);
	}




	@Test
	void enviaEmail_QuandoBemSucedido() {
        Email email = new Email();
        email.setEmail("bruno@unirio.br");
        email.setMensagem("teste externo");
        HttpResponse<String> response = Unirest.post(BASE_URL+"/enviaEmail")
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
    }


}


