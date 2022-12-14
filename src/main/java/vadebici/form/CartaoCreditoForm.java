package vadebici.form;

import vadebici.dom.CartaoCredito;

public class CartaoCreditoForm {
	
	private String nomeTitular;
	private String numero;
	private String validade;
	private String cvv;
	
	public CartaoCreditoForm(String nomeTitular, String numero, String validade, String cvv){
		this.nomeTitular = nomeTitular;
		this.numero = numero;
		this.validade = validade;
		this.cvv = cvv;
	}

	public CartaoCredito deserialize(){
		return new CreditCard(this.nomeTitular, this.numero, this.validade, this.cvv);
	}
}

