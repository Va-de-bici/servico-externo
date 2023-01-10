package vadebici.dom;

import java.util.Queue;

public class FilaCobranca {
	
	private static Queue<CobrancaD> queueCobranca;

	public static void inserir(CobrancaD cobranca) {
		queueCobranca.add(cobranca);
	}
	
	public Queue<CobrancaD> getQueueCobranca() {
		return queueCobranca;
	}

	public static void setQueueCobranca(Queue<CobrancaD> queueCobranca) {
		FilaCobranca.queueCobranca = queueCobranca;
	}

	public void setQueueCobranca(CobrancaD cobranca) {
		FilaCobranca.queueCobranca.add(cobranca);
	}
}
