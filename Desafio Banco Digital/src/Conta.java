import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Conta implements IConta {
	
	LocalDateTime horaAtual = LocalDateTime.now();
	DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm:ss");
	String dataHoraFormatada = horaAtual.format(formatoDataHora);

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	private int tipoDeTransacao = 0;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		tipoDeTransacao = 1;
		saldo -= valor;
		imprimirExtratoUltimaTransacao(tipoDeTransacao, valor);
	}

	@Override
	public void depositar(double valor) {
		tipoDeTransacao = 2;
		saldo += valor;
		imprimirExtratoUltimaTransacao(tipoDeTransacao, valor);
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	public void investir(double valor, double taxadeJuros, int periodoMeses) {
		Investimento investimento = new Investimento(valor, taxadeJuros, periodoMeses);
		investimento.calcularRendimento();
		investimento.detalhesDoInvestimento();
	}

	public void imprimirExtratoUltimaTransacao(int tipoDeTransacao, double valor) {
		switch (tipoDeTransacao) {
			case 1:
				System.out.println("Origem: Saque");
				System.out.println("Valor: "+ valor);
				System.out.println(dataHoraFormatada);
				tipoDeTransacao = 0;
				break;
		
			case 2:
				System.out.println("Origem: Depósito");
				System.out.println("Valor: "+ valor);
				System.out.println(dataHoraFormatada);
				tipoDeTransacao = 0;
				break;
			
			default:
				break;
		}
	}

	public void cartao(int numeroTelefone) {

		
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
