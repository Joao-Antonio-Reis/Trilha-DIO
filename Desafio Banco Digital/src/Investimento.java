public class Investimento {
    private double valorInvestido;
    private double taxaDeJuros;
    private int periodoMeses;

    public Investimento(double valorInvestido, double taxaDeJuros, int periodoMeses) {
        this.valorInvestido = valorInvestido;
        this.taxaDeJuros = taxaDeJuros;
        this.periodoMeses = periodoMeses;
    }

    public double calcularRendimento() {
        return valorInvestido * Math.pow(1 + taxaDeJuros, periodoMeses);
    }

    public void detalhesDoInvestimento() {
        System.out.println("Valor investido: R$" + valorInvestido);
        System.out.println("Taxa de juros: " + (taxaDeJuros * 100) + "% ao mês");
        System.out.println("Período: " + periodoMeses + " meses");
        System.out.println("Rendimento total após período: R$" + calcularRendimento());
    }
}
