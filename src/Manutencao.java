import java.time.LocalDateTime;

public class Manutencao {
    private int id;
    private Bicicleta bicicleta;

    public Manutencao(int id, Bicicleta bicicleta) {
        this.id = id;
        this.bicicleta = bicicleta;
    }

    public void solicitarConserto() {
        this.bicicleta.atualizarEstado("CONSERTO");
        System.out.println("Manutenção iniciada para a bicicleta ID: " + this.bicicleta.getId());
    }

    public void finalizarConserto() {
        this.bicicleta.atualizarEstado("ESTOQUE");
        this.bicicleta.zerarHorasDeUso();

        System.out.println("Manutenção finalizada! Bicicleta ID " + this.bicicleta.getId() + " pronta para uso no estoque.");
    }
}