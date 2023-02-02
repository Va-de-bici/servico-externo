package vadebici.models;

import vadebici.dom.Cartao;
import vadebici.dom.Ciclista;
import vadebici.dom.Cobranca;
import vadebici.dom.CobrancaRequisicao;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PagamentoModelos {

    public static Cobranca getCobranca() {
        Cobranca cobranca = new Cobranca();
        cobranca.setId("123");
        cobranca.setValor(123.0);
        cobranca.setCiclista("123");
        cobranca.setStatus("APPROVED");
        cobranca.setHoraSolicitacao(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 30, 0).getTime());
        cobranca.setHoraFinalizacao(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 30, 0).getTime());

        return cobranca;
    }
    public static Ciclista getCiclista() {
        Ciclista ciclista = new Ciclista();
        ciclista.setId("1");
        ciclista.setCartao(getCartao());
        return ciclista;
    }

    public static CobrancaRequisicao getCobrancaRequisicao() {
        CobrancaRequisicao cobrancaRequisicao = new CobrancaRequisicao();
        cobrancaRequisicao.setCiclista("1");
        cobrancaRequisicao.setValor(10.0);
        return cobrancaRequisicao;
    }

    public static Cartao getCartao() {
        Cartao cartao = new Cartao();
        cartao.setNumero("5155901222280001");
        cartao.setCvv("123");
        cartao.setValidade("2023-07-11");
        cartao.setNomeTitular("BRUNO BASTOS");
        return cartao;
    }
}
