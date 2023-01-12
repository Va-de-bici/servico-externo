package vadebici.form;

import vadebici.dom.Email;

public class EmailForm {

	public String message;
	public String email;
	
	public EmailForm() {

	}

	public EmailForm(String message, String email) {
		this.message = message;
		this.email = email;
	}

	public Email deserialize() {
		return new Email(this.message, this.email);
	}
}
