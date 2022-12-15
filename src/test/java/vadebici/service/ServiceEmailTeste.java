package vadebici.service;

import org.junit.jupiter.api.Test;
import vadebici.dom.Email;
import vadebici.service.ServiceEmail;
import org.junit.jupiter.api.Assertions;

public class ServiceEmailTeste {

    private final ServiceEmail serviceEmail = new ServiceEmail();

    @Test
    void enviaEmail_QuandoBemSucedido() {
        Email email = criaEmail();
        Email emailResponse = Assertions.assertDoesNotThrow(() -> serviceEmail.enviaMensagem(email));
        Assertions.assertEquals(email.getEmail(), emailResponse.getEmail());
        Assertions.assertEquals(email.getMensagem(), emailResponse.getMensagem());
    }

    Email criaEmail() {
        Email email = new Email();
        email.setEmail("teste@edu.unirio.br");
        email.setMensagem("Externo");
        return email;
    }
}


