package vadebici.service;

import org.junit.jupiter.api.Test;
import vadebici.dom.RequisicaoEmail;

import static org.junit.jupiter.api.Assertions.*;

public class ServicoEmailTest {

    private final ServicoEmail servicoEmail = new ServicoEmail();

    @Test
    void enviaEmail_QuandoBemSucedido() {
        RequisicaoEmail requisicaoEmail = createEmail();
        assertDoesNotThrow(() -> servicoEmail.enviarMensagem(requisicaoEmail));
    }

    RequisicaoEmail createEmail() {
        RequisicaoEmail requisicaoEmail = new RequisicaoEmail();
        requisicaoEmail.setEmail("brunobastos@edu.unirio.br");
        requisicaoEmail.setMensagem("Testando");
        return requisicaoEmail;
    }
}
