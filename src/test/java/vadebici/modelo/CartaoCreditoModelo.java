package vadebici.modelo;

import vadebici.dom.CartaoCredito;

public class CartaoCreditoModelo {

    public static CartaoCredito getCartao() {
        CartaoCredito cartao = new CartaoCredito();
        cartao.setNumero("5155901222280001");
        cartao.setCvv("333");
        cartao.setValidade("2025-01-01");
        cartao.setNomeTitular("Bruno Teste");
        return cartao;
    }
}


