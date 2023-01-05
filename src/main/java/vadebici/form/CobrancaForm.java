package vadebici.form;

import java.util.UUID;
import dom.CobrancaD;

public class CobrancaForm {
	
	private String id;
	private String ciclistaId;
	private int valor;
	private String horaSolicitacao;
	private String horaFinalizacao;	
	private String status;

	public CobrancaForm() {
		
	}
	
	public CobrancaForm(String id, String ciclistaId, String horaSolicitacao, String horaFinalizacao, int valor, Status status){
		this.id = id;
		this.ciclistaId = ciclistaId;
		this.horaSolicitacao = horaSolicitacao;
		this.horaFinalizacao = horaFinalizacao;
		this.valor = valor;
		this.status = status;
	}
	
	public CobrancaForm(String ciclistaId,  int valor){
		this.id =  UUID.randomUUID().toString();
		this.ciclistaId = ciclistaId;
		this.valor = valor;
	}
	
	public Cobranca unserialize(){
		return new Cobranca(this.id, this.ciclistaId, this.horaSolicitacao, this.horaFinalizacao, this.valor, this.status);
	}
	
	public Cobranca deserializePostCobranca(){
		return new Cobranca(this.ciclistaId, this.valor);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCiclistaId() {
		return ciclistaId;
	}

	public void setCiclistaId(String ciclistaId) {
		this.ciclistaId = ciclistaId;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getHoraSolicitacao() {
		return horaSolicitacao;
	}

	public void setHoraSolicitacao(String horaSolicitacao) {
		this.horaSolicitacao = horaSolicitacao;
	}

	public String getHoraFinalizacao() {
		return horaFinalizacao;
	}

	public void setHoraFinalizacao(String horaFinalizacao) {
		this.horaFinalizacao = horaFinalizacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
