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

    private Bicicleta bicicleta;
    private Cliente cliente;

    public Aluguel(){}

    public Aluguel(int id, Cliente cliente, Bicicleta bicicleta, double tempoDeUso, double valorTotal, String statusPagamento) {
        this.id = id;
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.tempoDeUso = tempoDeUso;
        this.valorTotal = valorTotal;
        this.statusPagamento = statusPagamento;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public void realizarAluguel() {
        this.id = contadorId ++;
        this.dataHoraRetirada = LocalDateTime.now();
        this.bicicleta.atualizarEstado("ALUGADA");
    }

    public void registrarDevolucao(double horasSimuladas) {
        this.dataHoraDevolucao = LocalDateTime.now();

        this.tempoDeUso = horasSimuladas;
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
        this.valorTotal = this.tempoDeUso * this.bicicleta.getTaxaPorHora();

        // Garante que o cliente pague pelo menos 1 hora de taxa mínima, caso devolva rápido
        if (this.valorTotal < this.bicicleta.getTaxaPorHora()) {
            this.valorTotal = this.bicicleta.getTaxaPorHora();
        }
    }

    public void verificarPagamento() {
        this.statusPagamento = "PAGO";
        System.out.println("Pagamento de R$ " + this.valorTotal + " confirmado para o aluguel #" + this.id);
    }

    public void gerarResumo() {
        System.out.println("RESUMO DO ALUGUEL #" + this.id);
        System.out.println("Bicicleta ID: " + this.bicicleta.getId());
        System.out.println("Tempo de Uso: " + this.tempoDeUso + " horas");
        System.out.println("Valor Total: R$ " + this.valorTotal);
        System.out.println("Status: " + this.statusPagamento);
        System.out.println("===============================");
    }

    public void setBicicleta(Bicicleta bicicleta) { this.bicicleta = bicicleta; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Cliente getCliente() { return cliente; }
    public Bicicleta getBicicleta() { return bicicleta; }
    public double getTempoDeUso() { return tempoDeUso; }
    public String getStatusPagamento() { return statusPagamento; }
    public double getValorTotal() { return this.valorTotal; }
    public int getId() { return this.id; }
}
