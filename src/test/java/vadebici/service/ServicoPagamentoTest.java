package vadebici.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vadebici.dom.Cartao;
import vadebici.dom.Ciclista;
import vadebici.dom.Cobranca;
import vadebici.dom.CobrancaRequisicao;
import vadebici.exception.GenericApiException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.when;
import static vadebici.models.PagamentoModelos.*;

class ServicoPagamentoTest {

    private ServicoPagamento servicoPagamento;
    @BeforeEach
    void setUp() {
        ServicoAluguel servicoAluguelMock = Mockito.mock(ServicoAluguel.class);
        servicoPagamento = new ServicoPagamento(servicoAluguelMock);

        when(servicoAluguelMock.getCiclista(any())).thenReturn(getCiclista());
    }
    @Test
    void validaCartao_RetornaVerificado_QuandoBemSucedido() {
        Cartao cartaoValido = getCartao();
        String status = servicoPagamento.validarCartao(cartaoValido);
        assertEquals("VERIFIED", status);
    }

    @Test
    void validarCartao_LancaExcecao_QuandoCartaoInvalido() {
        Cartao cartaoInvalido = getCartao();
        cartaoInvalido.setNumero("123");
        assertThrows(GenericApiException.class, () -> servicoPagamento.validarCartao(cartaoInvalido));

    }

    @Test
    void validarCartao_LancaExcecao_QuandoInformacoesIncompletas() {
        Cartao cartaoInvalido = getCartao();
        cartaoInvalido.setNomeTitular(null);
        assertThrows(GenericApiException.class, () -> servicoPagamento.validarCartao(cartaoInvalido));

    }

    @Test
    void realizarCobranca_RealizaTransacao_QuandoBemSucedido() {
        CobrancaRequisicao cobrancaRequisicao = getCobrancaRequisicao();
        Ciclista ciclista = getCiclista();
        Cobranca resultado = servicoPagamento.realizarCobranca(getCobrancaRequisicao());
        assertNotNull(resultado.getId());
        assertEquals(cobrancaRequisicao.getValor(), resultado.getValor());
        assertEquals(ciclista.getId(), resultado.getCiclista());
        assertEquals("APPROVED", resultado.getStatus());
    }

    @Test
    void realizarCobranca_LancaExcecao_QuandoValorIncorreto() {
        CobrancaRequisicao cobrancaRequisicao = getCobrancaRequisicao();
        cobrancaRequisicao.setValor(-1.0);
        GenericApiException ex = assertThrows(GenericApiException.class, () -> servicoPagamento.realizarCobranca(cobrancaRequisicao));
        assertEquals(422, ex.getCodigo());
    }
}
