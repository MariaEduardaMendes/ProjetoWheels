import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private List<Aluguel> historicoDeTransacoes;

    public Cliente() {
        this.historicoDeTransacoes = new ArrayList<>();
    }

    public void cadastrar() {
    }

    public void atualizarDados() {
    }

    public void consultarHistorico() {
    }
}
