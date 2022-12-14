package vadebici.service;

import org.junit.jupiter.api.Test;
import vadebici.dom.Email;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceEmailTeste {

    private final ServiceEmail serviceEmail = new ServiceEmail();

    @Test
    void enviaEmail_QuandoBom() {
        Email email = criaEmail();
        assertDoesNotThrow(() -> serviceEmail.enviaMensagem(email));
    }

    Email criaEmail() {
        Email email = new Email();
        email.setEmail("bruno@unirio.br");
        email.setMensagem("Teste externo");
        return email;
    }
}

