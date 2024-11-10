import java.util.ArrayList;
import java.util.List;
public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	private List<Extrato> listaDeExtratos = new ArrayList<>();

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if(valor > saldo){
			System.out.println("Saldo insuficiente!");
		}
		saldo -= valor;
		listaDeExtratos.add(new Extrato("Saque", valor));
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
		listaDeExtratos.add(new Extrato("Depósito", valor));
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if (valor > saldo) {
			System.out.println("Saldo insuficiente!");
		}
		this.sacar(valor);
		contaDestino.depositar(valor);
		listaDeExtratos.add(new Extrato("Tranferência", valor));
	}

	public void investir(double valor, double taxadeJuros, int periodoMeses) {
		Investimento investimento = new Investimento(valor, taxadeJuros, periodoMeses);
		investimento.calcularRendimento();
		investimento.detalhesDoInvestimento();
	}

	public void imprimirExtrato() {
		System.out.println("========Extrato========");
		for (Extrato extrato : listaDeExtratos) {
			System.out.println(extrato);
		}
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
