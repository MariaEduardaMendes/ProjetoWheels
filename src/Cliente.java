import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private List<Aluguel> historicoDeTransacoes;

    public Cliente(String nome, String cpf, String telefone) {
        this.id = contadorId++;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.historicoDeTransacoes = new ArrayList<>();
    }

    // Construtor especifico para ler o csv e recriar o objeto na memoria
    public Cliente(int id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.historicoDeTransacoes = new ArrayList<>();
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public void adicionarAoHistorico(Aluguel aluguel) {
        this.historicoDeTransacoes.add(aluguel);
    }

    public void consultarHistorico() {
        System.out.println("\nHistórico do Cliente " + this.nome);
        if (this.historicoDeTransacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.");
            return;
        }
        for (Aluguel aluguel : this.historicoDeTransacoes) {
            aluguel.gerarResumo();
    }
}

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf(){ return cpf;}
    public String getTelefone(){ return telefone;}
}
