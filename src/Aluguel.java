import java.time.Duration;
import java.time.LocalDateTime;

public class Aluguel {
    private static int contadorId = 1;
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
        this.id = contadorId ++;
        this.dataHoraRetirada = LocalDateTime.now();
        this.bicicleta.atualizarEstado("ALUGADA");
    }

    public void registrarDevolucao() {
        this.dataHoraDevolucao = LocalDateTime.now();

        Duration duracao = Duration.between(dataHoraRetirada, dataHoraDevolucao);
        this.tempoDeUso = duracao.toMinutes() / 60.0;

        this.bicicleta.registrarHorasDeUso(this.tempoDeUso);

        if (this.bicicleta.verificarNecessidadeManutencao()) {
            notificarManutencao();
        } else {
            this.bicicleta.atualizarEstado("ESTOQUE");
        }

        calcularValor();
    }

    private void notificarManutencao() {
        System.out.println("ALERTA DO SISTEMA");
        System.out.println("A bicicleta ID " + this.bicicleta.getId() + " atingiu o limite de horas de uso!");
        System.out.println("Notificação enviada ao Administrador e Mecânico.");

        this.bicicleta.atualizarEstado("CONSERTO");
    }

    public void calcularValor() {
    }

    public void verificarPagamento() {
    }

    public void gerarResumo() {
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
