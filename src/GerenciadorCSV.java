import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCSV {
    private static final String CSV_CLIENTES = "data/clientes.csv";
    private static final String CSV_BICICLETAS = "data/bicicletas.csv";
    private static final String CSV_ALUGUEIS = "data/alugueis.csv";

    public List<Cliente> carregarClientes() {
        List<Cliente> clientesLidos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_CLIENTES))) {
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
        try (FileWriter fw = new FileWriter(CSV_CLIENTES, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(cliente.getId() + "," + cliente.getNome() + "," + cliente.getCpf() + "," + cliente.getTelefone());

        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível salvar no arquivo CSV.");
        }
    }

    public List<Bicicleta> carregarBicicletas() {
        List<Bicicleta> bicicletasLidas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_BICICLETAS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    int id = Integer.parseInt(dados[0]);
                    String modelo = dados[1];
                    String estado = dados[2];
                    double horasDeUso = Double.parseDouble(dados[3]);
                    double taxaPorHora = Double.parseDouble(dados[4]);

                    bicicletasLidas.add(new Bicicleta(id, modelo, estado, horasDeUso, taxaPorHora));
                }
            }
            System.out.println(">> " + bicicletasLidas.size() + " bicicletas carregadas do CSV.");
        } catch (IOException e) {
            System.out.println(">> Arquivo de bicicletas vazio ou não encontrado.");
        }
        return bicicletasLidas;
    }

    public void salvarBicicletas(List<Bicicleta> listaBicicletas) {
        try (FileWriter fw = new FileWriter(CSV_BICICLETAS, false); PrintWriter pw = new PrintWriter(fw)) {
            for (Bicicleta bicicleta : listaBicicletas) {
                pw.println(bicicleta.getId() + "," +
                        bicicleta.getModelo() + "," +
                        bicicleta.getEstado() + "," +
                        bicicleta.getHorasDeUso() + "," +
                        bicicleta.getTaxaPorHora());
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível salvar a lista de bicicletas.");
        }
    }

    public List<Aluguel> carregarAlugueis(List<Cliente> clientes, List<Bicicleta> bicicletas, List<Aluguel> alugueisAtivos) {
        List<Aluguel> todosAlugueis = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_ALUGUEIS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 6) {
                    int idAluguel = Integer.parseInt(dados[0]);
                    int idCliente = Integer.parseInt(dados[1]);
                    int idBicicleta = Integer.parseInt(dados[2]);
                    double tempoDeUso = Double.parseDouble(dados[3]);
                    double valorTotal = Double.parseDouble(dados[4]);
                    String statusPagamento = dados[5];

                    Cliente clienteEncontrado = null;
                    for (Cliente c : clientes) {
                        if (c.getId() == idCliente) clienteEncontrado = c;
                    }

                    Bicicleta bicicletaEncontrada = null;
                    for (Bicicleta b : bicicletas) {
                        if (b.getId() == idBicicleta) bicicletaEncontrada = b;
                    }

                    if (clienteEncontrado != null && bicicletaEncontrada != null) {
                        Aluguel aluguel = new Aluguel(idAluguel, clienteEncontrado, bicicletaEncontrada, tempoDeUso, valorTotal, statusPagamento);
                        todosAlugueis.add(aluguel);

                        if ("PAGO".equals(statusPagamento)) {
                            clienteEncontrado.adicionarAoHistorico(aluguel);
                        } else {
                            alugueisAtivos.add(aluguel);
                        }
                    }
                }
            }
            System.out.println(">> " + todosAlugueis.size() + " aluguéis carregados do CSV.");
        } catch (IOException e) {
            System.out.println(">> Arquivo de aluguéis vazio ou não encontrado.");
        }
        return todosAlugueis;
    }

    public void salvarTodosAlugueis(List<Aluguel> listaCompleta) {
        try (FileWriter fw = new FileWriter(CSV_ALUGUEIS, false); PrintWriter pw = new PrintWriter(fw)) {
            for (Aluguel aluguel : listaCompleta) {
                pw.println(aluguel.getId() + "," +
                        aluguel.getCliente().getId() + "," +
                        aluguel.getBicicleta().getId() + "," +
                        aluguel.getTempoDeUso() + "," +
                        aluguel.getValorTotal() + "," +
                        aluguel.getStatusPagamento());
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível salvar o histórico de aluguéis.");
        }
    }
}