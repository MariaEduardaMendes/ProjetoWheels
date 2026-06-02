import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCSV {
    private static final String CAMINHO_ARQUIVO = "data/clientes.csv";

    public List<Cliente> carregarClientes() {
        List<Cliente> clientesLidos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    String cpf = dados[2];
                    String telefone = dados[3];
                    clientesLidos.add(new Cliente(id, nome, cpf, telefone));
                }
            }
            System.out.println(">> " + clientesLidos.size() + " clientes carregados do CSV.");
        } catch (IOException e) {
            System.out.println(">> Nenhum arquivo de dados encontrado. Iniciando banco de dados vazio.");
        }
        return clientesLidos;
    }

    public void salvarCliente(Cliente cliente) {
        try (FileWriter fw = new FileWriter(CAMINHO_ARQUIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(cliente.getId() + "," + cliente.getNome() + "," + cliente.getCpf() + "," + cliente.getTelefone());

        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível salvar no arquivo CSV.");
        }
    }
}
