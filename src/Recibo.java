import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Recibo {
    private int id;
    private LocalDateTime dataEmissao;
    private double valorTotalConsolidado;
    private List<Aluguel> alugueis;

    public void emitirRecibo() {
    }
}