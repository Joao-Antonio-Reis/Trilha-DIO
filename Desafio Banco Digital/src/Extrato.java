import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Extrato {

    private String tipoDeTransacao;
    private double valor;
    private String dataHora;

    public Extrato(String tipoDeTransacao, double valor){
        this.tipoDeTransacao = tipoDeTransacao;
        this.valor = valor;
        DateTimeFormatter formatoDiaMesAno = DateTimeFormatter.ofPattern("dd/MM/yyyy hh,mm,ss");
        this.dataHora = LocalDateTime.now().format(formatoDiaMesAno);
    }

    public String getTipoDeTransacao() {
        return tipoDeTransacao;
    }

    public double getValor() {
        return valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Extrato [tipoDeTransacao=" + tipoDeTransacao + ", valor=" + valor + ", dataHora=" + dataHora + "]";
    }
}
