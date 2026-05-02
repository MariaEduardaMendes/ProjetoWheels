import java.time.LocalDateTime;

public class Aluguel {
    private int id;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucao;
    private double tempoDeUso;
    private double valorTotal;
    private String statusPagamento;

    private Cliente cliente;
    private Bicicleta bicicleta;
    private Recibo recibo;

    public void realizarAluguel() {
    }

    public void registrarDevolucao() {
    }

    public void calcularValor() {
    }

    public void verificarPagamento() {
    }

    public void gerarResumo() {
    }
}
