import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Recibo {
    private LocalDateTime dataEmissao;
    private double valorTotalConsolidado;
    private List<Aluguel> alugueis;

    public Recibo() {
        this.alugueis = new ArrayList<>();
        this.dataEmissao = LocalDateTime.now();
    }

    public void adicionarAluguel(Aluguel aluguel) {
        this.alugueis.add(aluguel);
        this.valorTotalConsolidado += aluguel.getValorTotal();
    }

    public void emitirRecibo() {
        System.out.println("\n=================================");
        System.out.println("          RECIBO FINAL           ");
        System.out.println("Data: " + this.dataEmissao);
        System.out.println("---------------------------------");
        System.out.println("Itens faturados (" + this.alugueis.size() + " aluguéis):");

        for (Aluguel aluguel : this.alugueis) {
            System.out.println("- Aluguel #" + aluguel.getId() + " | Valor: R$ " + aluguel.getValorTotal());
        }

        System.out.println("---------------------------------");
        System.out.println("TOTAL A PAGAR: R$ " + this.valorTotalConsolidado);
        System.out.println("=================================\n");
    }
}