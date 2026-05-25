import java.time.LocalDateTime;

public class Manutencao {
    private int id;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataConclusao;
    private String status;
    private Bicicleta bicicleta;

    public Manutencao(int id, Bicicleta bicicleta) {
        this.id = id;
        this.bicicleta = bicicleta;
        this.status = "PENDENTE";
    }

    public void solicitarConserto() {
        this.dataSolicitacao = LocalDateTime.now();
        this.status = "EM_ANDAMENTO";

        this.bicicleta.atualizarEstado("CONSERTO");

        System.out.println("Manutenção iniciada para a bicicleta ID: " + this.bicicleta.getId());
    }

    public void finalizarConserto() {
        this.dataConclusao = LocalDateTime.now();
        this.status = "CONCLUIDA";

        this.bicicleta.atualizarEstado("ESTOQUE");

        this.bicicleta.zerarHorasDeUso();

        System.out.println("Manutenção finalizada! Bicicleta ID " + this.bicicleta.getId() + " pronta para uso no estoque.");
    }
}